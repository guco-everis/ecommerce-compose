package com.example.ecommerce.ui.product

import com.example.ecommerce.ui.base.Event

sealed class ProductEvent: Event {
    object UpdateCart: ProductEvent()
}