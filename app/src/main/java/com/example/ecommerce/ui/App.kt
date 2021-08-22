package com.example.ecommerce.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.*
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.ui.base.LocalAppNavigation
import com.example.ecommerce.ui.base.LocalUpdateCart
import com.example.ecommerce.ui.base.rememberNavigationGraph
import com.example.ecommerce.ui.navigation.AppNavigation

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(){
    val navigation = rememberNavigationGraph {
        AppNavigation(it)
    }
    EcommerceTheme {
        CompositionLocalProvider(
            LocalAppNavigation provides navigation,
            LocalUpdateCart provides remember {
                mutableStateOf({})
            }
        ) {
            navigation.Build()
        }
    }
}