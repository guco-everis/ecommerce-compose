package com.example.ecommerce.ui.base

object UpdateCart {

    private val listeners = mutableListOf<() -> Unit>()

    fun addListener(listener: () -> Unit){
        listeners.add(listener)
    }

    fun removeListener(listener: () -> Unit){
        listeners.remove(listener)
    }

    fun execute(){
        listeners.forEach {
            it()
        }
    }

}