package com.example.ecommerce.ui.shopping_cart

import com.example.ecommerce.domain.use_cases.action_cart.ActionCartParameters
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartResult
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartUseCase
import com.example.ecommerce.domain.use_cases.cart.CartParameters
import com.example.ecommerce.domain.use_cases.cart.CartResult
import com.example.ecommerce.domain.use_cases.cart.CartUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.shopping_cart.models.ShoppingScreenCartModel

class ShoppingCartViewModel(
    private val actionCartUseCase: ActionCartUseCase,
    private val cartUseCase: CartUseCase
): ScreenViewModel<ShoppingScreenCartModel, ShoppingCartEvent>(
    initModel = ShoppingScreenCartModel()
) {

    init {
        updateCart()
    }

    fun updateCart() = launch {
        when(val result = cartUseCase.execute(CartParameters())){
            is CartResult.Success -> {
                model?.items = model?.items?.toSuccess(newValue = result.items)
            }
            is CartResult.Error -> {
                model?.items = model?.items?.toError(msg = "Error")
            }
        }
        updateModel()
    }

    fun plusProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Plus(productId = id))
        if(result is ActionCartResult.Success){
            model?.itemPlus(id)
            updateModel()
        }
    }

    fun minusProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Minus(productId = id))
        if(result is ActionCartResult.Success){
            model?.itemMinus(id)
            updateModel()
        }
    }

    fun deleteProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Delete(productId = id))
        if(result is ActionCartResult.Success){
            model?.itemDelete(id)
            updateModel()
        }
    }

    fun enabledProduct(id: String, enabled: Boolean) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Enabled(productId = id, enabled = enabled))
        if(result is ActionCartResult.Success){
            model?.itemEnabled(id, enabled)
            updateModel()
        }
    }
}