package com.example.projectsplashscreen.presentation.selecteditemscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectsplashscreen.presentation.common.AppTopBar
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun SelectedItemScreen(
    onBack: () -> Unit,
    sharedViewModel: JobSharedViewModel

) {

    // Observe the selected job
    val selectedJob by sharedViewModel.selectedJob.collectAsState()

    if (selectedJob != null) {
        Log.d("SelectedItemScreen", "Job selected: ${selectedJob!!.title}")
        Scaffold(
            topBar = {
                AppTopBar(
                    title = selectedJob!!.title ?: "Job Details",
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
                    text = selectedJob!!.title ?: "No Title",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Company: ${selectedJob!!.company ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Location: ${selectedJob!!.location ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${selectedJob!!.type ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Salary: ${selectedJob!!.salary ?: "Not specified"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Snippet: ${selectedJob!!.snippet ?: "No Description"}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                val link = selectedJob!!.link
                if (!link.isNullOrEmpty()) {
                    val context = LocalContext.current
                    ApplyNowButton(link = link, onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Log.e("SelectedItemScreen", "Invalid URL: $link", e)
                            // Optionally, show a Snackbar or Toast to inform the user
                        }
                    })
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
        Log.w("SelectedItemScreen", "No job selected.")
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

@Composable
fun ApplyNowButton(link: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp), // Ensures the button has a good touch target size
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "Apply Now", style = MaterialTheme.typography.titleMedium)
    }
}