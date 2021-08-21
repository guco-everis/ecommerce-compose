package com.example.ecommerce.domain.use_cases.action_cart

import com.example.ecommerce.domain.base.Result

sealed class ActionCartResult: Result {
    object Success: ActionCartResult()
    object Error: ActionCartResult()
}