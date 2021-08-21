package com.example.ecommerce.ui.home

import com.example.ecommerce.ui.base.Event

sealed class HomeEvent: Event {
    class GoToProduct(val id: String): HomeEvent()
}