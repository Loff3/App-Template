package com.example.projectsplashscreen.presentation

// NavGraph.kt

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projectsplashscreen.data.DataSource
import com.example.projectsplashscreen.presentation.HomeScreen.HomeScreen
import com.example.projectsplashscreen.presentation.SelectedItemScreen.SelectedItemScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    navActions: NavActions,
    startDestination: String = NavRoutes.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home Screen
        composable(
            route = NavRoutes.HOME_ROUTE,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            }
        ) {
            HomeScreen(
                onItemClick = { dataItem ->
                    navActions.navigateToDetail(dataItem.id)
                }
            )
        }
        // Detail Screen
        composable(
            route = "${NavRoutes.DETAIL_ROUTE}/{${NavArgs.ITEM_ID_ARG}}",
            arguments = listOf(
                navArgument(NavArgs.ITEM_ID_ARG) { type = NavType.IntType }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(300)
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(300)
                ) + fadeOut(animationSpec = tween(300))
            }
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(NavArgs.ITEM_ID_ARG) ?: return@composable
            val dataItem = DataSource().loadData().find { it.id == itemId }
            if (dataItem != null) {
                SelectedItemScreen(
                    data = dataItem,
                    onBack = { navActions.navigateBack() }
                )
            } else {
                // Handle item not found
                Text("Item not found")
            }
        }
    }
}
