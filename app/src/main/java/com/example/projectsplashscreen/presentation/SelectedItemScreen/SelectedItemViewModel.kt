package com.example.projectsplashscreen.presentation.SelectedItemScreen

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SelectedItemViewModel : ViewModel() {
    // Dictionary
    private val _messages = mutableStateMapOf<Int, String>()

    val messages: Map<Int, String> get() = _messages

    fun getMessageFlow(itemId: Int): StateFlow<String> {
        // Create a StateFlow for the specific item ID
        return snapshotFlow { _messages[itemId] ?: "" }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _messages[itemId] ?: ""
        )
    }

    fun updateMessage(itemId: Int, message: String) {
        _messages[itemId] = message
    }
}