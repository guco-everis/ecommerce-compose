package com.example.ecommerce.domain.entities

data class Product(
    val id: String,
    val image: String,
    val name: String,
    val price: Double,
    val discount: Double,
    val points: Float,
    val deliveryPrice: Double
)