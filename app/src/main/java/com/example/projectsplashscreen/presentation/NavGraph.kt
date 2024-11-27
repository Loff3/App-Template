// AppNavGraph.kt
package com.example.projectsplashscreen.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectsplashscreen.NavGraphScope
import com.example.projectsplashscreen.presentation.homescreen.HomeScreen
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel
import com.example.projectsplashscreen.presentation.selecteditemscreen.SelectedItemScreen
import org.koin.compose.getKoin

import org.koin.core.scope.Scope

@Composable
fun AppNavGraph(
    navController: NavHostController,
    navActions: NavActions,
    startDestination: String = NavRoutes.HOME_ROUTE
) {
    // Obtain Koin instance
    val koin = getKoin()

    // Create or get the existing scope for the NavGraph
    val navGraphScope: Scope = remember {
        koin.createScope("NavGraphScopeId", NavGraphScope)
    }

    // Obtain the scoped JobSharedViewModel
    val sharedViewModel: JobSharedViewModel = navGraphScope.get<JobSharedViewModel>()

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
                },
                sharedViewModel = sharedViewModel
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
                onBack = { navActions.navigateBack() },
                sharedViewModel = sharedViewModel
            )
        }
    }
}
