package com.example.ecommerce.domain.use_cases

import com.example.ecommerce.domain.entities.Category
import com.example.ecommerce.domain.entities.Product
import com.example.ecommerce.domain.entities.ShoppingCartItem

object Storage {
    val products = listOf(
        Product(
            id = "0",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 12.00,
            points = 4f,
            deliveryPrice = 0.0
        ),
        Product(
            id = "1",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 12.00,
            points = 4f,
            deliveryPrice = 0.0
        ),
        Product(
            id = "2",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 12.00,
            points = 4f,
            deliveryPrice = 0.0
        ),
        Product(
            id = "3",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 12.00,
            points = 4f,
            deliveryPrice = 0.0
        ),
        Product(
            id = "4",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 0.0,
            points = 4f,
            deliveryPrice = 0.0
        ),
        Product(
            id = "5",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            name = "Hamburguesa",
            price = 16.00,
            discount = 0.0,
            points = 4f,
            deliveryPrice = 0.0
        )
    )
    val categories = listOf(
        Category(
            id = "0",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            text = "Comida rapida",
            backgroundColor = "#FF6200EE",
            textColor = "#FFFFFFFF"
        ),
        Category(
            id = "1",
            image = "https://static.wikia.nocookie.net/hayday/images/3/31/Zanahoria.png/revision/latest?cb=20200823211755&path-prefix=es",
            text = "Vegetales",
            backgroundColor = "#FF01b484",
            textColor = "#FFFFFFFF"
        ),
        Category(
            id = "2",
            image = "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a8.png",
            text = "Bebidas",
            backgroundColor = "#FFffcf00",
            textColor = "#FFFFFFFF"
        ),
        Category(
            id = "3",
            image = "https://lh3.googleusercontent.com/proxy/5Sg-AkIaKyU-P6Zvuq9uRb_88jmchEI8UB9qqL8CRzTexM4Sl_CGcDF35W9B8K_8MU1_OiXWwsDrSwH1qaGe32rTfm0Bph2TkHlkHeG4MFRudiQVcE0M1d7t3YCR5Xxv4tCoyU-Wal_CFDPwZ7U_49Gt_59sIac06Pyu0Ug-0tr1ca8uVa2CszosgWcCXKH5g8vzO2VL82q2-e_v",
            text = "Carnes",
            backgroundColor = "#FFfc746a",
            textColor = "#FFFFFFFF"
        )
    )
    val shoppingCartItems = mutableListOf<ShoppingCartItem>()
}