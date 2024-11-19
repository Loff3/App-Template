package com.example.projectsplashscreen.presentation.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ItemList(
    dataList: List<DataClass1>,
    modifier: Modifier = Modifier,
    onItemClick: (DataClass1) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(dataList) { dataItem ->
            ItemCard(
                data = dataItem,
                onItemClick = onItemClick
            )
        }
    }
}
