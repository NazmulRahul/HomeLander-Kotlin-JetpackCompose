package com.example.myapplication.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilteredDataViewModel:ViewModel() {
    var filteredList= mutableStateOf(FilteredUiState())
}