package com.example.projectsplashscreen.presentation.jobs

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectsplashscreen.data.JoobleApiService.JobDataClassMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Scope to the right activity to ensure their is a owner
class JobSharedViewModel : ViewModel() {
    // Holds the mutable state in private val
    private val _selectedJob = MutableStateFlow<JobDataClassMode?>(null)

    // Exposes the state as immutable
    val selectedJob: StateFlow<JobDataClassMode?> = _selectedJob

    // Set job
    fun setSelectJob(job: JobDataClassMode) {
        if (job != null) {
            _selectedJob.value = job
            Log.d("SelectedItemScreen", "Job selected: ${job.title}")

        } else {
            Log.w("SelectedItemScreen", "No job selected.")

        }

    }
}