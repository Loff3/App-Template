// NavGraph.kt
package com.example.projectsplashscreen.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                onNavigateToSelectedItem = {
                    navActions.navigateToSelectedItem()
                }
            )
        }
        // Selected Item Screen
        composable(
            route = NavRoutes.SELECTED_ITEM_ROUTE,
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
        ) {
            SelectedItemScreen(
                onBack = { navActions.navigateBack() }
            )
        }
    }
}
