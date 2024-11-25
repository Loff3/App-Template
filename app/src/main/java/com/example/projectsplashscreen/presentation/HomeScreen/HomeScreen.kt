// HomeScreen.kt
package com.example.projectsplashscreen.presentation.HomeScreen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectsplashscreen.presentation.common.AppTopBar
import com.example.projectsplashscreen.presentation.common.ItemList
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel
import com.example.projectsplashscreen.viewmodel.JobsViewModel

@Composable
fun HomeScreen(
    onNavigateToSelectedItem: () -> Unit = {}
) {
    val jobsViewModel: JobsViewModel = viewModel()
    val activity = LocalContext.current as ComponentActivity
    val sharedViewModel: JobSharedViewModel = viewModel(activity)
    val uiState by jobsViewModel.jobsState.collectAsState()

    // Fetch jobs on first launch
    LaunchedEffect(Unit) {
        jobsViewModel.getJobs(
            searchParams = com.example.projectsplashscreen.data.JoobleApiService.JobSearchParams(
                keywords = "it",
                location = "Stockholm"
            )
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Jobs",
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        ItemList(
            jobDataClassList = when (uiState) {
                is JobsViewModel.UiState.Success -> (uiState as JobsViewModel.UiState.Success).data.jobDataClasses
                else -> emptyList()
            },
            onJobClick = { jobDataClass ->
                // Store the selected job in the SharedViewModel
                sharedViewModel.setSelectJob(jobDataClass)
                // Navigate to SelectedItemScreen
                onNavigateToSelectedItem()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
