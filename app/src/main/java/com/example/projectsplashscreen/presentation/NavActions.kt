package com.example.projectsplashscreen.presentation

// NavigationActions.kt

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Models the navigation actions in the app.
 */
class NavActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(NavRoutes.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToDetail(itemId: Int) {
        navController.navigate("${NavRoutes.DETAIL_ROUTE}/$itemId")
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}
