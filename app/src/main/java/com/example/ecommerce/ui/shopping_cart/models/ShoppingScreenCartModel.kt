package com.example.ecommerce.ui.shopping_cart.models

import com.example.ecommerce.domain.entities.ShoppingCartItem
import com.example.ecommerce.ui.base.Model
import com.example.ecommerce.ui.base.StatusValue

data class ShoppingScreenCartModel(
    var items: StatusValue<List<ShoppingCartItem>>? = StatusValue.Loading()
): Model{
    val price get() = items?.value?.filter { it.enabled }?.sumOf { it.total } ?: 0.0
    val delivery get() = items?.value?.filter { it.enabled }?.sumOf { it.product.deliveryPrice } ?: 0.0
    val totalPrice get() = price + delivery

    fun itemPlus(id: String){
        item(id)?.let {
            it.units++
        }
    }

    fun itemMinus(id: String){
        item(id)?.let {
            it.units--
        }
    }

    fun itemDelete(id: String){
        items = items?.toSuccess(
            newValue = items?.value?.filter { it.product.id != id }
        )
    }

    fun itemEnabled(id: String, enabled: Boolean){
        item(id)?.enabled = enabled
    }

    fun item(id: String): ShoppingCartItem? = items?.value?.find { it.product.id == id }
}
