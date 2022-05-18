package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.models.ShopItem
import com.example.shoppinglist.domain.interfaces.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}