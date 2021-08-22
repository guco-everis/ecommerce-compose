package com.example.ecommerce.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navArgument
import com.example.ecommerce.ui.base.NavigationGraph
import com.example.ecommerce.ui.main.MainScreen
import com.example.ecommerce.ui.product.ProductScreen

class AppNavigation(navHostController: NavHostController): NavigationGraph(navHostController) {

    fun navigateToProduct(productId: String){
        navHostController.navigate(PRODUCT.setValue("productId", productId))
    }

    @Composable
    override fun Build() {
        NavHost(
            navController = navHostController,
            startDestination = MAIN
        ){
            composable(MAIN){
                MainScreen()
            }
            fullScreenDialog(
                PRODUCT,
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

    companion object {
        const val PRODUCT = "product/{productId}"
        const val MAIN = "main"
    }

}