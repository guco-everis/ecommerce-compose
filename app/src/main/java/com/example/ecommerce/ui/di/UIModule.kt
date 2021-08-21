package com.example.ecommerce.ui.di

import com.example.ecommerce.ui.home.HomeViewModel
import com.example.ecommerce.ui.product.ProductViewModel
import com.example.ecommerce.ui.product.models.ProductScreenModel
import com.example.ecommerce.ui.shopping_cart.ShoppingCartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val UIModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ShoppingCartViewModel(get(), get())}
    viewModel { (productId: String) -> ProductViewModel(productId, get(), get()) }
}