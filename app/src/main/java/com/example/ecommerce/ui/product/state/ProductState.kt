package com.example.ecommerce.ui.product.state

import com.example.ecommerce.domain.entities.Product
import com.example.ecommerce.ui.base.State
import com.example.ecommerce.ui.base.StatusValue

data class ProductState(
    var product: StatusValue<Product?> = StatusValue.Loading(null),
    var units: Int = 1
): State