package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.interfaces.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}