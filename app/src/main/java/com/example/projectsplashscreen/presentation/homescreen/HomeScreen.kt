// HomeScreen.kt
package com.example.projectsplashscreen.presentation.homescreen
import androidx.activity.ComponentActivity

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectsplashscreen.presentation.common.AppTopBar
import com.example.projectsplashscreen.presentation.common.ItemList
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext
import com.example.projectsplashscreen.data.JoobleApiService.JobSearchParams
import com.example.projectsplashscreen.presentation.searchcomponents.GeneralSearchBar
import com.example.projectsplashscreen.presentation.searchcomponents.LocationSearchBar
import com.example.projectsplashscreen.presentation.searchcomponents.SearchViewModel
import com.example.projectsplashscreen.presentation.jobs.JobsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    onNavigateToSelectedItem: () -> Unit,
    sharedViewModel: JobSharedViewModel

) {
    val jobsViewModel: JobsViewModel = koinViewModel()
    val searchViewModel: SearchViewModel = koinViewModel()

    // Observe
    val uiState by jobsViewModel.jobsState.collectAsState()

    // Observe the search queries
    val generalQuery by searchViewModel.generalQuery.collectAsState()
    val locationQuery by searchViewModel.locationQuery.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Jobs",
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Row for the search button and the two search bars
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Search Button
                Button(
                    onClick = {
                        // Trigger the search action
                        jobsViewModel.getJobs(
                            searchParams = JobSearchParams(
                                keywords = generalQuery,
                                location = locationQuery
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(0.3f)
                        .height(56.dp) // Match the height of the TextFields
                ) {
                    Text("Search")
                }

                // General Search Bar
                GeneralSearchBar(
                    query = generalQuery,
                    onQueryChange = { searchViewModel.setGeneralQuery(it) },
                    modifier = Modifier.weight(1f),
                    onSearch = {
                        // Do nothing or handle if you want to trigger search when the user presses search on the keyboard
                    }
                )

                // Location Search Bar
                LocationSearchBar(
                    query = locationQuery,
                    onQueryChange = { searchViewModel.setLocationQuery(it) },
                    modifier = Modifier.weight(1f),
                    onSearch = {
                        // Do nothing or handle if you want to trigger search when the user presses search on the keyboard
                    }
                )
            }

            // Job List
            ItemList(
                jobDataClassModeList = when (uiState) {
                    is JobsViewModel.UiState.Success -> (uiState as JobsViewModel.UiState.Success).data.jobDataClassModes
                    else -> emptyList()
                },

                onJobClick = { jobDataClass ->
                    // Store the selected job in the SharedViewModel
                    sharedViewModel.setSelectJob(jobDataClass)
                    // Navigate to SelectedItemScreen
                    onNavigateToSelectedItem()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
