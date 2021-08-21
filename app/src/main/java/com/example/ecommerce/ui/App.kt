package com.example.ecommerce.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.EcommerceTheme
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.ecommerce.ui.base.LocalNavController
import com.example.ecommerce.ui.base.LocalUpdateCart
import com.example.ecommerce.ui.base.Route
import com.example.ecommerce.ui.main.MainScreen
import com.example.ecommerce.ui.product.ProductScreen

@Composable
fun App(){
    val navHostController = rememberNavController()
    EcommerceTheme {
        CompositionLocalProvider(
            LocalNavController provides navHostController,
            LocalUpdateCart provides remember {
                mutableStateOf({})
            }
        ) {
            NavHost(
                navController = navHostController,
                startDestination = Route.MAIN
            ){
                composable(Route.MAIN){
                    MainScreen()
                }
                composable(
                    Route.PRODUCT,
                    arguments = listOf(
                        navArgument("productId") { type = NavType.StringType }
                    )
                ){
                    ProductScreen(
                        productId = it.arguments?.getString("productId") ?: ""
                    )
                }
            }
        }
    }
}