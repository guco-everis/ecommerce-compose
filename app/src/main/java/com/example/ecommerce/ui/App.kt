package com.example.ecommerce.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.base.LocalAppNavigation
import com.example.ecommerce.ui.base.rememberNavigationGraph
import com.example.ecommerce.ui.navigation.AppNavigation

@Composable
fun App(){
    val navigation = rememberNavigationGraph {
        AppNavigation(it)
    }
    EcommerceTheme {
        CompositionLocalProvider(
            LocalAppNavigation provides navigation,
        ) {
            navigation.Build()
        }
    }
}