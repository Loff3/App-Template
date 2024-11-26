package com.example.projectsplashscreen.presentation.SearchComponents

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {

    // General search query
    private val _generalQuery = MutableStateFlow("")
    val generalQuery: StateFlow<String> = _generalQuery

    // Location search query
    private val _locationQuery = MutableStateFlow("")
    val locationQuery: StateFlow<String> = _locationQuery

    // Selected filter option
    private val _selectedFilterOption = MutableStateFlow("All")
    val selectedFilterOption: StateFlow<String> = _selectedFilterOption


    // Functions to update the queries
    fun setGeneralQuery(query: String) {
        _generalQuery.value = query
    }

    fun setLocationQuery(query: String) {
        _locationQuery.value = query
    }

    fun setSelectedFilterOption(option: String) {
        _selectedFilterOption.value = option
    }



    // Future Improvements_____________________________________

    // General suggestions
    private val _generalSuggestions = MutableStateFlow<List<String>>(emptyList())
    val generalSuggestions: StateFlow<List<String>> = _generalSuggestions

    // Location suggestions
    private val _locationSuggestions = MutableStateFlow<List<String>>(emptyList())
    val locationSuggestions: StateFlow<List<String>> = _locationSuggestions

    // Functions to update suggestions
    fun updateGeneralSuggestions(query: String) {
        // Fetch or generate suggestions based on the query
        _generalSuggestions.value = listOf("Suggestion 1", "Suggestion 2", "Suggestion 3")
    }

    fun updateLocationSuggestions(query: String) {
        // Fetch or generate location suggestions
        _locationSuggestions.value = listOf("Stockholm", "Gothenburg", "Malmo")
    }
}
