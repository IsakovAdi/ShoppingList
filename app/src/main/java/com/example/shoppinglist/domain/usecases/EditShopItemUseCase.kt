package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.interfaces.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}