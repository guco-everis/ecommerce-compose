package com.example.ecommerce.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerce.ui.base.NavigationGraph
import com.example.ecommerce.ui.home.HomeScreen
import com.example.ecommerce.ui.shopping_cart.ShoppingCartScreen

class MainNavigation(navHostController: NavHostController): NavigationGraph(navHostController) {

    fun navigateToHome(){
        navHostController.navigate(HOME){
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToShoppingCart(){
        navHostController.navigate(SHOPPING_CART) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    @Composable
    override fun Build() {
        NavHost(
            navController = navHostController,
            startDestination = HOME,
        ) {
            composable(HOME){
                HomeScreen()
            }
            composable(SHOPPING_CART){
                ShoppingCartScreen()
            }
        }
    }

    companion object {
        const val HOME = "home"
        const val SHOPPING_CART = "shopping_cart"
    }
}