package com.example.projectsplashscreen.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

// Helper Function /extension

// Uses Dispatchers.IO for network call which is optimized for network operations
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): Result<T> {
    return withContext(dispatcher) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(NullPointerException("Response body is null"))
                }
            } else {
                // Parse the error response if possible
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseErrorMessage(errorBody)
                Result.failure(HttpException(response).apply {
                    // Optionally, you can attach the error message to the exception
                    initCause(Throwable(errorMessage))
                })
            }
        } catch (e: IOException) {
            // Network error (e.g., no internet connection)
            Result.failure(e)
        } catch (e: HttpException) {
            // Non-2xx HTTP error
            Result.failure(e)
        } catch (e: Exception) {
            // Any other error
            Result.failure(e)
        }
    }
}

// Helper function to parse error message
private fun parseErrorMessage(errorBody: String?): String {
    return if (!errorBody.isNullOrEmpty()) {
        try {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(ErrorResponse::class.java)
            val errorResponse = adapter.fromJson(errorBody)
            errorResponse?.message ?: "Unknown API error"
        } catch (e: Exception) {
            "Error parsing error response"
        }
    } else {
        "Unknown API error"
    }
}
