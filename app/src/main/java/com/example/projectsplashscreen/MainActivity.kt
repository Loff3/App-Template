package com.example.projectsplashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.projectsplashscreen.presentation.AppNavGraph
import com.example.projectsplashscreen.presentation.NavActions
import com.example.projectsplashscreen.ui.theme.ProjectSplashScreenTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            ProjectSplashScreenTheme {
                val windowSize = calculateWindowSizeClass(this)

                val navController = rememberNavController()
                val navActions = NavActions(navController)
                AppNavGraph(
                    navController,
                    navActions
                )
            }
        }
    }
}
