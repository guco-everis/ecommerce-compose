package com.example.ecommerce.domain.use_cases.categories

import com.example.ecommerce.domain.base.Result
import com.example.ecommerce.domain.entities.Category

sealed class CategoriesResult: Result {
    class Success(val categories: List<Category>): CategoriesResult()
    object Error: CategoriesResult()
}