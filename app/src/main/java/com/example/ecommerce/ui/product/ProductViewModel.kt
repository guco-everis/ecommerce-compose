package com.example.ecommerce.ui.product

import com.example.ecommerce.domain.use_cases.action_cart.ActionCartParameters
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartResult
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartUseCase
import com.example.ecommerce.domain.use_cases.products.ProductsParameters
import com.example.ecommerce.domain.use_cases.products.ProductsResult
import com.example.ecommerce.domain.use_cases.products.ProductsUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.product.models.ProductScreenModel

class ProductViewModel(
    private val productId: String,
    private val productsUseCase: ProductsUseCase,
    private val actionCartUseCase: ActionCartUseCase
): ScreenViewModel<ProductScreenModel, ProductEvent>(
    initModel = ProductScreenModel()
) {

    init {
        initProduct()
    }

    private fun initProduct() = launch {
        when(val result = productsUseCase.execute(ProductsParameters(id = productId))){
            is ProductsResult.Success -> {
                model?.product = model?.product?.toSuccess(
                    newValue = result.products.firstOrNull()
                )
            }
            is ProductsResult.Error -> {

            }
        }
        updateModel()
    }

    fun plusUnit(){
        model?.let {
            it.units++
            updateModel()
        }
    }

    fun minusUnit(){
        model?.let {
            if(it.units > 1){
                it.units--
                updateModel()
            }
        }
    }

    fun addProduct() = launch {
        when(actionCartUseCase.execute(ActionCartParameters.Add(
            productId = productId,
            units = model?.units ?: 1
        ))){
            is ActionCartResult.Success -> {
                event = ProductEvent.UpdateCart
            }
            is ActionCartResult.Error -> {

            }
        }
        updateModel()
    }

}