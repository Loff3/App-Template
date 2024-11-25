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
    onBack: () -> Unit
) {
    // Retrive the job object passed form home screen
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val sharedViewModel: JobSharedViewModel = viewModel(activity)

    // Observe the selected job by using collectAsState(
    val job by sharedViewModel.selectedJob.collectAsState()
    // Delegate to viemodel using "by"
    // !! tells program that it wont be null even tho its nullable

    if (job != null) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = job!!.title,
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
                // Display job image if available
                // Assuming you have a drawable resource for jobs; otherwise, you can skip the Image
                // Image(
                //     painter = painterResource(id = job.imageResource), // Replace with actual image resource if available
                //     contentDescription = job.title,
                //     modifier = Modifier
                //         .fillMaxWidth()
                //         .height(200.dp),
                //     contentScale = ContentScale.Crop
                // )
                // Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = job!!.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Company: ${job!!.company ?: "N/A"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Location: ${job!!.location}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${job!!.type}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Salary: ${job!!.salary ?: "Not specified"}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Snippet: ${job!!.snippet}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Apply Here:",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = job!!.link,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job!!.link))
                        context.startActivity(intent)

                    })
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
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: Could not find job.")
            }
        }
    }
}
