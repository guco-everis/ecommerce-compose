package com.example.ecommerce.domain.di

import com.example.ecommerce.domain.use_cases.action_cart.ActionCartUseCase
import com.example.ecommerce.domain.use_cases.cart.CartUseCase
import com.example.ecommerce.domain.use_cases.categories.CategoriesUseCase
import com.example.ecommerce.domain.use_cases.products.ProductsUseCase
import org.koin.dsl.module

val DomainModule = module {
    single { ProductsUseCase() }
    single { CategoriesUseCase() }
    single { ActionCartUseCase() }
    single { CartUseCase() }
}