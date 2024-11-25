package com.example.projectsplashscreen.presentation.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projectsplashscreen.data.JoobleApiService.JobDataClass

@Composable
fun ItemList(
    jobDataClassList: List<JobDataClass>,
    modifier: Modifier = Modifier,
    onJobClick: (JobDataClass) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(jobDataClassList) { jobDataClass ->
            ItemCard(
                jobDataClass = jobDataClass,
                onClick = { onJobClick(jobDataClass) }
            )
        }
    }
}
