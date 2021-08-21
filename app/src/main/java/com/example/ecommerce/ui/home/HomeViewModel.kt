package com.example.ecommerce.ui.home

import com.example.ecommerce.domain.use_cases.categories.CategoriesParameters
import com.example.ecommerce.domain.use_cases.categories.CategoriesResult
import com.example.ecommerce.domain.use_cases.categories.CategoriesUseCase
import com.example.ecommerce.domain.use_cases.products.ProductsParameters
import com.example.ecommerce.domain.use_cases.products.ProductsResult
import com.example.ecommerce.domain.use_cases.products.ProductsUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.home.models.HomeScreenModel

class HomeViewModel(
    private val productsUseCase: ProductsUseCase,
    private val categoriesUseCase: CategoriesUseCase
): ScreenViewModel<HomeScreenModel, HomeEvent>(initModel = HomeScreenModel()) {

    init {
        initProducts()
        initProductsOffSale()
        initCategories()
    }

    private fun initProducts() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(offSale = false))){
            is ProductsResult.Success -> {
                model?.products = model?.products?.toSuccess(
                    newValue = result.products
                )
            }
            is ProductsResult.Error -> {
                model?.products = model?.products?.toError(
                    msg = "Error"
                )
            }
        }
        updateModel()
    }

    private fun initProductsOffSale() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(offSale = true))){
            is ProductsResult.Success -> {
                model?.productsOffSale = model?.productsOffSale?.toSuccess(
                    newValue = result.products
                )
            }
            is ProductsResult.Error -> {
                model?.productsOffSale = model?.productsOffSale?.toError(
                    msg = "Error"
                )
            }
        }
        updateModel()
    }

    private fun initCategories() = launch {
        when(val result = categoriesUseCase.execute(CategoriesParameters())){
            is CategoriesResult.Success -> {
                model?.categories = model?.categories?.toSuccess(
                    newValue = result.categories
                )
            }
            is CategoriesResult.Error -> {
                model?.categories = model?.categories?.toError(
                    msg = "Error"
                )
            }
        }
        updateModel()
    }
}