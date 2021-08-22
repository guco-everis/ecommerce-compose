package com.example.ecommerce.ui.product

import com.example.ecommerce.domain.use_cases.action_cart.ActionCartParameters
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartResult
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartUseCase
import com.example.ecommerce.domain.use_cases.products.ProductsParameters
import com.example.ecommerce.domain.use_cases.products.ProductsResult
import com.example.ecommerce.domain.use_cases.products.ProductsUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.product.state.ProductState

class ProductViewModel(
    private val productId: String,
    private val productsUseCase: ProductsUseCase,
    private val actionCartUseCase: ActionCartUseCase
): ScreenViewModel<ProductState, ProductEvent>(
    state = ProductState()
) {

    init {
        initProduct()
    }

    private fun initProduct() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(id = productId))){
            is ProductsResult.Success -> {
                setState {
                    product = product.toSuccess(
                        newValue = result.products.firstOrNull()
                    )
                }
            }
            is ProductsResult.Error -> {

            }
        }
    }

    fun plusUnit(){
        setState {
            units++
        }
    }

    fun minusUnit(){
        setState {
            if(units > 1){
                units--
            }
        }
    }

    fun addProduct() = launch {
        when(actionCartUseCase.execute(ActionCartParameters.Add(
            productId = productId,
            units = state.units
        ))){
            is ActionCartResult.Success -> {
                event = ProductEvent.UpdateCart
            }
            is ActionCartResult.Error -> {

            }
        }
    }

}