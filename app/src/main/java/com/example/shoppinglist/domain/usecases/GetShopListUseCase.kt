package com.example.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.interfaces.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList():LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}