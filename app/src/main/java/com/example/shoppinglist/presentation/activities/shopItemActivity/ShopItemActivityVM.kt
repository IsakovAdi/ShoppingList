package com.example.shoppinglist.presentation.activities.shopItemActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.usecases.AddShopItemUseCase
import com.example.shoppinglist.domain.usecases.EditShopItemUseCase
import com.example.shoppinglist.domain.usecases.GetShopItemUseCase

class ShopItemActivityVM : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInPutName = MutableLiveData<Boolean>()
    val errorInPutName: LiveData<Boolean>
        get() = _errorInPutName

    private val _errorInPutCount = MutableLiveData<Boolean>()
    val errorInPutCount: LiveData<Boolean>
        get() = _errorInPutCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseActivity = MutableLiveData<Unit>()
    val shouldCloseActivity:LiveData<Unit>
    get() = _shouldCloseActivity

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
           finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
        // если inputName не равен null тогда мы вернем String и обрежем пробелы методом trim а иначе возвращаем пустой текст но не null
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInPutName.value = true
            result = false
        }
        if (count <= 0) {
            result = false
            _errorInPutCount.value = true
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInPutName.value = false
    }

    fun resetErrorInputCount() {
        _errorInPutCount.value = false
    }

    private fun finishWork(){
        _shouldCloseActivity.value = Unit
    }
}