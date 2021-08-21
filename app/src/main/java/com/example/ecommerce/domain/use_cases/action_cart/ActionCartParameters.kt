package com.example.ecommerce.domain.use_cases.action_cart

import com.example.ecommerce.domain.base.Parameters

sealed class ActionCartParameters(val productId: String): Parameters {
    class Add(productId: String, val units: Int): ActionCartParameters(productId)
    class Plus(productId: String): ActionCartParameters(productId)
    class Minus(productId: String): ActionCartParameters(productId)
    class Delete(productId: String): ActionCartParameters(productId)
    class Enabled(productId: String, val enabled: Boolean): ActionCartParameters(productId)
}