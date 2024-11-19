package com.example.projectsplashscreen.presentation.common

import androidx.annotation.DrawableRes

// Basic dataclass för a screen

data class DataClass1 (
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

