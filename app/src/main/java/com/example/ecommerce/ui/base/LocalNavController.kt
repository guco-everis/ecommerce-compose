package com.example.ecommerce.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> { error("Empty!") }

@Composable
fun localNavController() = LocalNavController.current