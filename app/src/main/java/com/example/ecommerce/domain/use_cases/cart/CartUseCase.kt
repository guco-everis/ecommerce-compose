package com.example.ecommerce.domain.use_cases.cart

import com.example.ecommerce.domain.base.UseCase
import com.example.ecommerce.domain.use_cases.Storage

class CartUseCase: UseCase<CartParameters, CartResult> {

    override suspend fun execute(params: CartParameters): CartResult = launch {
        return@launch CartResult.Success(
            items = Storage.shoppingCartItems.map { it.copy() }
        )
    }

}