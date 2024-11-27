package com.example.projectsplashscreen.presentation.searchcomponents

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction

@Composable
fun LocationSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { Text("Location") },
        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        // Removed keyboardActions to prevent triggering search on keyboard action
    )
}
