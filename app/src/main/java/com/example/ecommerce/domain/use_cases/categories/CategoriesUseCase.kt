package com.example.ecommerce.domain.use_cases.categories

import com.example.ecommerce.domain.base.UseCase
import com.example.ecommerce.domain.use_cases.Storage
import kotlinx.coroutines.delay

class CategoriesUseCase: UseCase<CategoriesParameters, CategoriesResult> {

    override suspend fun execute(params: CategoriesParameters): CategoriesResult = launch {
        delay(1000)
        return@launch CategoriesResult.Success(
            categories = Storage.categories
        )
    }

}