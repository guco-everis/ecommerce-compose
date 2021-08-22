package com.example.ecommerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.ecommerce.ui.base.NavigationGraph
import com.example.ecommerce.ui.main.MainScreen
import com.example.ecommerce.ui.product.ProductScreen

class AppNavigation(navHostController: NavHostController): NavigationGraph(navHostController) {

    fun navigateToProduct(productId: String){
        navHostController.navigate(PRODUCT.setValue("productId", productId))
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Build() {
        NavHost(
            navController = navHostController,
            startDestination = MAIN
        ){
            composable(MAIN){
                MainScreen()
            }
            dialog(
                PRODUCT,
                arguments = listOf(
                    navArgument("productId") { type = NavType.StringType }
                ),
                dialogProperties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ){
                ProductScreen(
                    productId = it.arguments?.getString("productId") ?: ""
                )
            }
        }
    }

    companion object {
        const val PRODUCT = "product/{productId}"
        const val MAIN = "main"
    }

}