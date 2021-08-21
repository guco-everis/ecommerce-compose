package com.example.ecommerce.ui.main

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.base.Route
import com.example.ecommerce.ui.home.HomeScreen
import com.example.ecommerce.ui.shopping_cart.ShoppingCartScreen

@Composable
fun MainScreen(){
    val navHostController = rememberNavController()
    Scaffold (
        backgroundColor = MaterialTheme.colors.primary,
        bottomBar = {
            HomeBottomAppBar(navHostController)
        }
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route
        ){
            composable(Route.HOME){
                HomeScreen()
            }
            composable(Route.SHOPPING_CART){
                ShoppingCartScreen()
            }
        }
    }
}

@Composable
fun HomeBottomAppBar(navHostController: NavHostController) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.drawTopLine(
            width = 1.dp,
            color = Color.LightGray.copy(alpha = 0.3f)
        ),
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.hierarchy
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.any { it.route == screen.route } == true,
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // evitar copias del mismo destino
                        launchSingleTop = true
                        // Restaurar el estado de un elemento preseleccionado
                        restoreState = true
                    }
                },
                icon = {
                    screen.icon()
                },
                selectedContentColor = MaterialTheme.colors.primary
            )
        }
    }
}

private sealed class Screen(
    val icon: @Composable () -> Unit,
    val route: String
){
    object Home: Screen(
        icon = {
            Icon(Icons.Outlined.Home, contentDescription = null)
        },
        route = Route.HOME
    )
    object ShoppingCart: Screen(
        icon = {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
        },
        route = Route.SHOPPING_CART
    )
}

private val items = listOf(
    Screen.Home,
    Screen.ShoppingCart
)

internal fun Modifier.drawTopLine(color: Color, width: Dp): Modifier{
    return drawBehind {
        val stroke = (width * density).value
        drawLine(
            color = color,
            start = Offset(
                x = 0f,
                y = 0f
            ),
            end = Offset(
                x = size.width,
                y = 0f
            ),
            strokeWidth = stroke
        )
    }
}