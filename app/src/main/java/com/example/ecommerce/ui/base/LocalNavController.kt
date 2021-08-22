package com.example.ecommerce.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.example.ecommerce.ui.navigation.AppNavigation

val LocalAppNavigation = compositionLocalOf<AppNavigation> { error("Empty!") }

@Composable
fun localAppNavigation() = LocalAppNavigation.current