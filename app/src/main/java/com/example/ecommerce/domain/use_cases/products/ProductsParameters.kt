package com.example.ecommerce.domain.use_cases.products

import com.example.ecommerce.domain.base.Parameters

data class ProductsParameters(
    val id: String? = null,
    val categoryId: Int? = null,
    val offSale: Boolean? = null
): Parameters