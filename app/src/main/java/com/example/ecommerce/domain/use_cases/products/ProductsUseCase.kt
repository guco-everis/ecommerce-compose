package com.example.ecommerce.domain.use_cases.products

import com.example.ecommerce.domain.base.UseCase
import com.example.ecommerce.domain.use_cases.Storage
import kotlinx.coroutines.delay

class ProductsUseCase: UseCase<ProductsParameters, ProductsResult> {

    override suspend fun execute(params: ProductsParameters): ProductsResult = launch {
        delay(1000)
        val (id, categoryId, offSale) = params
        var products = Storage.products.asSequence()
        products = when(offSale) {
            true -> products.filter { it.discount > 0.0 }
            false -> products.filter { it.discount == 0.0 }
            else -> products
        }
        products = when(id) {
            null -> products
            else -> products.filter { it.id == id }
        }
        return@launch ProductsResult.Success(
            products = products.toList()
        )
    }
}