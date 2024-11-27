package com.example.projectsplashscreen.data.JoobleApiService

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

// NETWORK LAYER

// API Service Interface
interface JoobleApiService {
    @Headers("Content-Type: application/json")
    @POST("{api_key}")
    suspend fun getJobs(
        @Path("api_key") apiKey: String,
        @Body request: JobsRequest
    ): Response<JobsResponse>
}

// Data Classes

data class JobsRequest(
    @Json(name = "keywords") val keywords: String,
    @Json(name = "location") val location: String,
    @Json(name = "radius") val radius: String? = null,
    @Json(name = "salary") val salary: Int? = null,
    @Json(name = "page") val page: Int = 1,
    @Json(name = "ResultOnPage") val resultOnPage: Int? = null,
    @Json(name = "SearchMode") val searchMode: Int? = null,
    @Json(name = "companysearch") val companySearch: Boolean? = null
)

data class JobDataClassMode(
    val title: String?,
    val location: String?,
    val snippet: String?,
    val salary: String?,
    val source: String?,
    val type: String?,
    val link: String?,
    val company: String?,
    val updated: String?,
    val id: Long?
)


data class JobsResponse(
    @Json(name = "totalCount") val totalCount: Int,
    @Json(name = "jobs") val jobDataClassModes: List<JobDataClassMode>
)

data class JobSearchParams(
    val keywords: String,
    val location: String,
    val radius: String? = null,
    val salary: Int? = null,
    val page: Int = 1,
    val resultOnPage: Int? = null,
    val searchMode: Int? = null,
    val companySearch: Boolean? = null
)
