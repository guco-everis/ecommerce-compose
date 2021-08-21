package com.example.ecommerce.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalUpdateCart = compositionLocalOf { mutableStateOf({}) }

@Composable
fun localUpdateCart() = LocalUpdateCart.current