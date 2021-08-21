package com.example.ecommerce.ui.base

object Route {
    const val HOME = "HOME_ROUTE"
    const val SHOPPING_CART = "SHOPPING_CART_ROUTE"
    const val PRODUCT = "PRODUCT_ROUTE/{productId}"
    const val MAIN = "MAIN_ROUTE"
}

fun String.setValue(id: String, value: String): String {
    return this.replace("{$id}", value)
}