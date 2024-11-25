// NavActions.kt
package com.example.projectsplashscreen.presentation

import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigateToSelectedItem() {
        navController.navigate(NavRoutes.SELECTED_ITEM_ROUTE)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}
