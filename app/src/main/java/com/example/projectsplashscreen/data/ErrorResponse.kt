package com.example.projectsplashscreen.data

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "error") val error: String,
    @Json(name = "message") val message: String
    // Add other fields as per your API's error response
)
