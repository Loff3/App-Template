package com.example.projectsplashscreen.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = { Text("Search Keywords") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    // Location Field
    OutlinedTextField(
        value = locationQuery,
        onValueChange = { locationQuery = it },
        label = { Text("Location") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
    // Search Button
    Button(
        onClick = {
            // Fetch jobs based on search query
            jobsViewModel.getJobs(
                searchParams = com.example.projectsplashscreen.data.JoobleApiService.JobSearchParams(
                    keywords = searchQuery,
                    location = locationQuery
                )
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Search")
    }
}