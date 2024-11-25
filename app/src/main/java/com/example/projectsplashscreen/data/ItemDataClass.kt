package com.example.projectsplashscreen.data

import androidx.annotation.DrawableRes

// Basic dataclass för a screen

data class ItemDataClass (
    val id: Int,
    val title: String,
    val description: String,
    var message: String, // Mutable
    @DrawableRes val image: Int
)


