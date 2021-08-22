package com.example.ecommerce.ui.shopping_cart.state

import com.example.ecommerce.domain.entities.ShoppingCartItem
import com.example.ecommerce.ui.base.State
import com.example.ecommerce.ui.base.StatusValue

data class ShoppingCartState(
    var items: StatusValue<List<ShoppingCartItem>> = StatusValue.Loading(listOf())
): State{

    val price get() = items.value.filter { it.enabled }.sumOf { it.total }
    val delivery get() = items.value.filter { it.enabled }.sumOf { it.product.deliveryPrice }
    val totalPrice get() = price + delivery

    private fun item(id: String): ShoppingCartItem? = items.value.find { it.product.id == id }

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
        items = items.toSuccess(
            newValue = items.value.filter { it.product.id != id }
        )
    }

    fun itemEnabled(id: String, enabled: Boolean){
        item(id)?.enabled = enabled
    }

}
