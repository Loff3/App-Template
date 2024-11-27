package com.example.projectsplashscreen.data.JoobleApiService

import com.example.projectsplashscreen.data.safeApiCall
import retrofit2.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface JobsRepository{
    suspend fun fetchJobs(apiKey: String, searchParams: JobSearchParams): Result<JobsResponse>
}

class JobsRepositoryImpl(
    private val apiService: JoobleApiService
) : JobsRepository {

    override suspend fun fetchJobs(
        apiKey: String,
        searchParams: JobSearchParams
    ): Result<JobsResponse> {
        return safeApiCall {
            apiService.getJobs(apiKey, JobsRequest(
                keywords = searchParams.keywords,
                location = searchParams.location,
                radius = searchParams.radius,
                salary = searchParams.salary,
                page = searchParams.page,
                resultOnPage = searchParams.resultOnPage,
                searchMode = searchParams.searchMode,
                companySearch = searchParams.companySearch
            ))
        }
    }
}