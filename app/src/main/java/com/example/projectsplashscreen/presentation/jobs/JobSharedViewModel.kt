package com.example.projectsplashscreen.presentation.jobs

import androidx.lifecycle.ViewModel
import com.example.projectsplashscreen.data.JoobleApiService.JobDataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JobSharedViewModel : ViewModel() {
    // Holds the mutable state in private val
    private val _selectedJob = MutableStateFlow<JobDataClass?>(null)

    // Exposes the state as immutable
    val selectedJob: StateFlow<JobDataClass?> = _selectedJob

    // Set job
    fun setSelectJob(job: JobDataClass) {
        _selectedJob.value = job
    }


}