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
import com.example.ecommerce.ui.base.rememberNavigationGraph
import com.example.ecommerce.ui.main.navigation.MainNavigation

@Composable
fun MainScreen(){
    val navigation = rememberNavigationGraph {
        MainNavigation(it)
    }
    Scaffold (
        backgroundColor = MaterialTheme.colors.primary,
        bottomBar = {
            HomeBottomAppBar(navigation)
        }
    ) {
        navigation.Build()
    }
}

@Composable
fun HomeBottomAppBar(navigation: MainNavigation) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.drawTopLine(
            width = 1.dp,
            color = Color.LightGray.copy(alpha = 0.3f)
        ),
    ) {
        val currentDestination by navigation.currentDestinationAsState()
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination.compareRoute(screen.route),
                onClick = {
                    when(screen.route){
                        MainNavigation.HOME -> navigation.navigateToHome()
                        MainNavigation.SHOPPING_CART -> navigation.navigateToShoppingCart()
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
        route = MainNavigation.HOME
    )
    object ShoppingCart: Screen(
        icon = {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = null)
        },
        route = MainNavigation.SHOPPING_CART
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