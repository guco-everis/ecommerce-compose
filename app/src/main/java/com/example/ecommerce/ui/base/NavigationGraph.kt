package com.example.ecommerce.ui.base

import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import androidx.navigation.NavGraphBuilder

abstract class NavigationGraph(protected val navHostController: NavHostController) {

    @OptIn(ExperimentalComposeUiApi::class)
    protected fun NavGraphBuilder.fullScreenDialog(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        deepLinks: List<NavDeepLink> = emptyList(),
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        addDestination(
            DialogNavigator.Destination(
                provider[DialogNavigator::class],
                DialogProperties(
                    usePlatformDefaultWidth = false
                ),
                content
            ).apply {
                this.route = route
                arguments.forEach { (argumentName, argument) ->
                    addArgument(argumentName, argument)
                }
                deepLinks.forEach { deepLink ->
                    addDeepLink(deepLink)
                }
            }
        )
    }

    fun String.setValue(id: String, value: String): String {
        return this.replace("{$id}", value)
    }

    @Composable
    abstract fun Build()

    @Composable
    fun currentDestinationAsState(): State<CurrentDestination>{
        val currentDestination = remember { mutableStateOf(CurrentDestination()) }
        navHostController.currentBackStackEntryAsState()
        DisposableEffect(this) {
            val callback = NavController.OnDestinationChangedListener { controller, _, _ ->
                currentDestination.value = CurrentDestination(controller.currentBackStackEntry)
            }
            navHostController.addOnDestinationChangedListener(callback)
            onDispose {
                navHostController.removeOnDestinationChangedListener(callback)
            }
        }
        return currentDestination
    }

}

class CurrentDestination(private val navBackStackEntry: NavBackStackEntry? = null){
    fun compareRoute(route: String): Boolean {
        return navBackStackEntry?.destination?.hierarchy?.any { it.route == route } == true
    }
}

@Composable
fun <T: NavigationGraph>rememberNavigationGraph(calculation: @DisallowComposableCalls (navHostController: NavHostController) -> T): T {
    val navHostController = rememberNavController()
    return remember {
        calculation(navHostController)
    }
}