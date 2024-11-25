package com.example.projectsplashscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectsplashscreen.data.JoobleApiService.JobsRepository
import com.example.projectsplashscreen.data.JoobleApiService.JobsResponse
import com.example.projectsplashscreen.data.JoobleApiService.JobSearchParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JobsViewModel : ViewModel() {

    private val repository = JobsRepository()

    private val _jobsState = MutableStateFlow<UiState>(UiState.Loading)
    val jobsState: StateFlow<UiState> = _jobsState

    private val API_KEY = "105e7c47-351b-494b-9104-6522b8d30bae" // Replace with BuildConfig for security

    fun getJobs(searchParams: JobSearchParams) {
        viewModelScope.launch {
            _jobsState.value = UiState.Loading
            val result = repository.fetchJobs(
                apiKey = API_KEY,
                searchParams = searchParams
            )
            _jobsState.value = result.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it) }
            )
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val data: JobsResponse) : UiState()
        data class Error(val exception: Throwable) : UiState()
    }
}
