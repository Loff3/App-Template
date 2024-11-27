package com.example.projectsplashscreen.presentation.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectsplashscreen.data.JoobleApiService.JobsResponse
import com.example.projectsplashscreen.data.JoobleApiService.JobSearchParams
import com.example.projectsplashscreen.data.JoobleApiService.JobsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.projectsplashscreen.BuildConfig



class JobsViewModel(
    private val repository: JobsRepository
) : ViewModel() {

    private val _jobsState = MutableStateFlow<UiState>(UiState.Loading)
    val jobsState: StateFlow<UiState> = _jobsState

    private val apiKey = BuildConfig.API_KEY

    fun getJobs(searchParams: JobSearchParams) {
        // Validate search parameters
        if (searchParams.keywords.isBlank() && searchParams.location.isBlank()) {
            _jobsState.value = UiState.Error(
                IllegalArgumentException("At least one search parameter is needed.")
            )
            return
        }

        viewModelScope.launch {
            _jobsState.value = UiState.Loading
            val result = repository.fetchJobs(
                apiKey = apiKey,
                searchParams = searchParams
            )
            result.fold(
                onSuccess = { jobsResponse: JobsResponse ->
                    _jobsState.value = UiState.Success(jobsResponse)
                },
                onFailure = { throwable: Throwable ->
                    _jobsState.value = UiState.Error(throwable)
                }
            )
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: JobsResponse) : UiState()
        data class Error(val exception: Throwable) : UiState()
    }
}