package com.example.projectsplashscreen.presentation.HomeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectsplashscreen.data.DataSource
import com.example.projectsplashscreen.presentation.common.AppTopBar
import com.example.projectsplashscreen.presentation.common.DataClass1
import com.example.projectsplashscreen.presentation.common.ItemList

@Preview
@Composable
fun HomeScreen(
    onItemClick: (DataClass1) -> Unit = {}
) {
    val dataList = DataSource().loadData()
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Home",
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        ItemList(
            dataList = dataList,
            onItemClick = onItemClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
