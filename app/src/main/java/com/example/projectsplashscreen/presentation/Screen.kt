package com.example.projectsplashscreen.presentation

sealed class Screen(val route: String) {
    object Home : Screen("home") // Singletons
    object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}
