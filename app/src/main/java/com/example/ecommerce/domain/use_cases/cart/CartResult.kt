package com.example.ecommerce.domain.use_cases.cart

import com.example.ecommerce.domain.base.Result
import com.example.ecommerce.domain.entities.ShoppingCartItem

sealed class CartResult: Result {
    class Success(val items: List<ShoppingCartItem>): CartResult()
    object Error: CartResult()
}