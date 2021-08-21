package com.example.ecommerce.domain.entities

data class ShoppingCartItem(
    val product: Product,
    var units: Int,
    var enabled: Boolean
){
    val total get() = units * product.price
}