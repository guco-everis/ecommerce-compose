package com.example.ecommerce.ui.product.models

import com.example.ecommerce.domain.entities.Product
import com.example.ecommerce.ui.base.Model
import com.example.ecommerce.ui.base.StatusValue

data class ProductScreenModel(
    var product: StatusValue<Product>? = StatusValue.Loading(),
    var units: Int = 1
): Model