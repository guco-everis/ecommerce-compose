package com.example.ecommerce.domain.use_cases.action_cart

import com.example.ecommerce.domain.base.UseCase
import com.example.ecommerce.domain.entities.ShoppingCartItem
import com.example.ecommerce.domain.use_cases.Storage

class ActionCartUseCase: UseCase<ActionCartParameters, ActionCartResult> {
    override suspend fun execute(params: ActionCartParameters): ActionCartResult = launch {
        return@launch when(params) {
            is ActionCartParameters.Plus -> {
                val result = Storage.shoppingCartItems.find { it.product.id == params.productId }
                if(result != null){
                    result.units++
                    ActionCartResult.Success
                } else {
                    ActionCartResult.Error
                }
            }
            is ActionCartParameters.Minus -> {
                val result = Storage.shoppingCartItems.find { it.product.id == params.productId }
                if(result != null){
                    if(result.units >= 2){
                        result.units--
                        ActionCartResult.Success
                    }else{
                        ActionCartResult.Error
                    }
                } else {
                    ActionCartResult.Error
                }
            }
            is ActionCartParameters.Delete -> {
                val result = Storage.shoppingCartItems.find { it.product.id == params.productId }
                if(result != null){
                    Storage.shoppingCartItems.remove(result)
                    ActionCartResult.Success
                } else {
                    ActionCartResult.Error
                }
            }
            is ActionCartParameters.Enabled -> {
                val result = Storage.shoppingCartItems.find { it.product.id == params.productId }
                if(result != null){
                    result.enabled = params.enabled
                    ActionCartResult.Success
                } else {
                    ActionCartResult.Error
                }
            }
            is ActionCartParameters.Add -> {
                val item = Storage.shoppingCartItems.find { it.product.id == params.productId }
                if(item != null){
                    item.units = params.units
                    return@launch ActionCartResult.Success
                }else{
                    val result = Storage.products.find { it.id == params.productId }
                    if(result != null){
                        Storage.shoppingCartItems.add(
                            ShoppingCartItem(
                                product = result,
                                units = params.units,
                                enabled = true
                            )
                        )
                        ActionCartResult.Success
                    }else{
                        ActionCartResult.Error
                    }
                }

            }
        }

    }
}