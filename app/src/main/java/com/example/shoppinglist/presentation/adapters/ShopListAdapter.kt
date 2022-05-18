package com.example.shoppinglist.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.presentation.ShopItemDiffCallback
import com.example.shoppinglist.presentation.ShopItemViewHolder

//////////////////////////////////////////////////////////////////////////////
//class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.Holder>() {
//    var shopList = listOf<ShopItem>()
//        set(value) {
//            val callback = ShopListDiffCallback(shopList, value)
//            val difResult = DiffUtil.calculateDiff(callback)    // работает в основном потоке
//            difResult.dispatchUpdatesTo(this)
//            field = value
//        }
//var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
//var onShopItemClickListener: ((ShopItem) -> Unit)? = null
////////////////////////////////////////////////////////////////////////////////

class ShopListAdapter : androidx.recyclerview.widget.ListAdapter<ShopItem,
        ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null
//    ////////////////////////////////////////////////////////////////////////

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 5

    }
}