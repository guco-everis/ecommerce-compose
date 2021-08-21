package com.example.ecommerce.ui.home.models

import com.example.ecommerce.domain.entities.Category
import com.example.ecommerce.domain.entities.Product
import com.example.ecommerce.ui.base.Model
import com.example.ecommerce.ui.base.StatusValue

data class HomeScreenModel(
    var productsOffSale: StatusValue<List<Product>>? = StatusValue.Loading(),
    var products: StatusValue<List<Product>>? = StatusValue.Loading(),
    var categories: StatusValue<List<Category>>? = StatusValue.Loading()
): Model