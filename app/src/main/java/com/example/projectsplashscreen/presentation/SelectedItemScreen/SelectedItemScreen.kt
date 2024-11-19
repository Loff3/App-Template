package com.example.projectsplashscreen.presentation.SelectedItemScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projectsplashscreen.presentation.common.DataClass1
import com.example.projectsplashscreen.presentation.common.AppTopBar

@Composable
fun SelectedItemScreen(
    data: DataClass1,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = data.title,
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = data.image),
                contentDescription = data.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
