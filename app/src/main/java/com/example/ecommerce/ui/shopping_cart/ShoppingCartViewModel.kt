package com.example.ecommerce.ui.shopping_cart

import com.example.ecommerce.domain.use_cases.action_cart.ActionCartParameters
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartResult
import com.example.ecommerce.domain.use_cases.action_cart.ActionCartUseCase
import com.example.ecommerce.domain.use_cases.cart.CartParameters
import com.example.ecommerce.domain.use_cases.cart.CartResult
import com.example.ecommerce.domain.use_cases.cart.CartUseCase
import com.example.ecommerce.ui.base.ScreenViewModel
import com.example.ecommerce.ui.base.UpdateCart
import com.example.ecommerce.ui.shopping_cart.state.ShoppingCartState

class ShoppingCartViewModel(
    private val actionCartUseCase: ActionCartUseCase,
    private val cartUseCase: CartUseCase
): ScreenViewModel<ShoppingCartState, ShoppingCartEvent>(
    state = ShoppingCartState()
) {

    init {
        UpdateCart.addListener(::updateCart)
        updateCart()
    }

    private fun updateCart() = launch {
        when(val result = cartUseCase.execute(CartParameters())){
            is CartResult.Success -> {
                setState {
                    items = items.toSuccess(
                        newValue = result.items
                    )
                }
            }
            is CartResult.Error -> {
                setState {
                    items = items.toError(
                        msg = "Error"
                    )
                }
            }
        }
    }

    fun plusProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Plus(productId = id))
        if(result is ActionCartResult.Success){
            setState {
                itemPlus(id)
            }
        }
    }

    fun minusProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Minus(productId = id))
        if(result is ActionCartResult.Success){
            setState {
                itemMinus(id)
            }
        }
    }

    fun deleteProduct(id: String) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Delete(productId = id))
        if(result is ActionCartResult.Success){
            setState {
                itemDelete(id)
            }
        }
    }

    fun enabledProduct(id: String, enabled: Boolean) = launch {
        val result = actionCartUseCase.execute(ActionCartParameters.Enabled(productId = id, enabled = enabled))
        if(result is ActionCartResult.Success){
            setState {
                itemEnabled(id, enabled)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        UpdateCart.removeListener(::updateCart)
    }
}