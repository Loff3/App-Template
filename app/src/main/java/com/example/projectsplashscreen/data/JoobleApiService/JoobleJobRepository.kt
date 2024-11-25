package com.example.projectsplashscreen.data.JoobleApiService

import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JobsRepository {

    suspend fun fetchJobs(
        apiKey: String,
        searchParams: JobSearchParams
    ): Result<JobsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = JobsRequest(
                    keywords = searchParams.keywords,
                    location = searchParams.location,
                    radius = searchParams.radius,
                    salary = searchParams.salary,
                    page = searchParams.page,
                    resultOnPage = searchParams.resultOnPage,
                    searchMode = searchParams.searchMode,
                    companySearch = searchParams.companySearch
                )
                val response = JoobleApi.retrofitService.getJobs(apiKey, request)
                Result.success(response)
            } catch (e: HttpException) {
                when (e.code()) { // Corrected access to code()
                    403 -> Result.failure(Exception("Access denied – Invalid API key."))
                    404 -> Result.failure(Exception("Not found – The requested endpoint or resource is not available."))
                    else -> Result.failure(Exception("HTTP error ${e.code()}: ${e.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
