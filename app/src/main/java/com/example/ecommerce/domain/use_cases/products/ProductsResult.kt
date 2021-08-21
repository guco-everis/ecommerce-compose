package com.example.ecommerce.domain.use_cases.products

import com.example.ecommerce.domain.base.Result
import com.example.ecommerce.domain.entities.Product

sealed class ProductsResult: Result {
    class Success(
        val products: List<Product>
    ): ProductsResult()
    object Error: ProductsResult()
}