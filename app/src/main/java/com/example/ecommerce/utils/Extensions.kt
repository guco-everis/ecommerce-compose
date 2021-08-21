package com.example.ecommerce.utils

fun Number.format(digits: Int): String{
    return "%.${digits}f".format(this).replace(",",".")
}