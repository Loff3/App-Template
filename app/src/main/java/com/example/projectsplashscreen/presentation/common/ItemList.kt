// ItemList.kt
package com.example.projectsplashscreen.presentation.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projectsplashscreen.data.JoobleApiService.JobDataClassMode

@Composable
fun ItemList(
    jobDataClassModeList: List<JobDataClassMode>,
    modifier: Modifier = Modifier,
    onJobClick: (JobDataClassMode) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(jobDataClassModeList) { jobDataClass ->
            ItemCard(
                jobDataClassMode = jobDataClass,
                onClick = { onJobClick(jobDataClass) }
            )
        }
    }
}