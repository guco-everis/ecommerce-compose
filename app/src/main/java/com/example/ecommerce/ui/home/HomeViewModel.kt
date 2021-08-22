package com.example.ecommerce.ui.home

import com.example.ecommerce.domain.use_cases.categories.CategoriesParameters
import com.example.ecommerce.domain.use_cases.categories.CategoriesResult
import com.example.ecommerce.domain.use_cases.categories.CategoriesUseCase
import com.example.ecommerce.domain.use_cases.products.ProductsParameters
import com.example.ecommerce.domain.use_cases.products.ProductsResult
import com.example.ecommerce.domain.use_cases.products.ProductsUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.home.state.HomeState

class HomeViewModel(
    private val productsUseCase: ProductsUseCase,
    private val categoriesUseCase: CategoriesUseCase
): ScreenViewModel<HomeState, HomeEvent>(state = HomeState()) {

    init {
        initProducts()
        initProductsOffSale()
        initCategories()
    }

    private fun initProducts() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(offSale = false))){
            is ProductsResult.Success -> {
                setState {
                    products = products.toSuccess(
                        newValue = result.products
                    )
                }
            }
            is ProductsResult.Error -> {
                setState {
                    products = products.toError(
                        msg = "Error"
                    )
                }
            }
        }
    }

    private fun initProductsOffSale() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(offSale = true))){
            is ProductsResult.Success -> {
                setState {
                    productsOffSale = productsOffSale.toSuccess(
                        newValue = result.products
                    )
                }
            }
            is ProductsResult.Error -> {
                setState {
                    productsOffSale = productsOffSale.toError(
                        msg = "Error"
                    )
                }
            }
        }
    }

    private fun initCategories() = launch {
        when(val result = categoriesUseCase.execute(CategoriesParameters())){
            is CategoriesResult.Success -> {
                setState {
                    categories = categories.toSuccess(
                        newValue = result.categories
                    )
                }
            }
            is CategoriesResult.Error -> {
                setState {
                    categories = categories.toError(
                        msg = "Error"
                    )
                }
            }
        }
    }
}