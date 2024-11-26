package com.example.projectsplashscreen.presentation.SelectedItemScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectsplashscreen.data.JoobleApiService.JobDataClass
import com.example.projectsplashscreen.presentation.common.AppTopBar
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun SelectedItemScreen(
    sharedViewModel: JobSharedViewModel,
    onBack: () -> Unit
) {
    // Observe the selected job
    val job by sharedViewModel.selectedJob.collectAsState()

    if (job != null) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = job!!.title ?: "Job Details",
                    canNavigateBack = true,
                    navigateUp = onBack
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = job!!.title ?: "No Title",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Company: ${job!!.company ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Location: ${job!!.location ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${job!!.type ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Salary: ${job!!.salary ?: "Not specified"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Snippet: ${job!!.snippet ?: "No Description"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Apply Here:",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))

                val link = job!!.link
                if (!link.isNullOrEmpty()) {
                    val context = LocalContext.current
                    Text(
                        text = link,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                            context.startActivity(intent)
                        }
                    )
                } else {
                    Text(
                        text = "No Link Available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                }
            }
        }
    } else {
        // Handle null case
        Scaffold(
            topBar = {
                AppTopBar(
                    title = "Job Details",
                    canNavigateBack = true,
                    navigateUp = onBack
                )
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: Could not find job.")
                }
            }
        )
    }
}
