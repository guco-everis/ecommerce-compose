package com.example.ecommerce.ui.home.state

import com.example.ecommerce.domain.entities.Category
import com.example.ecommerce.domain.entities.Product
import com.example.ecommerce.ui.base.State
import com.example.ecommerce.ui.base.StatusValue

data class HomeState(
    var productsOffSale: StatusValue<List<Product>> = StatusValue.Loading(listOf()),
    var products: StatusValue<List<Product>> = StatusValue.Loading(listOf()),
    var categories: StatusValue<List<Category>> = StatusValue.Loading(listOf())
): State