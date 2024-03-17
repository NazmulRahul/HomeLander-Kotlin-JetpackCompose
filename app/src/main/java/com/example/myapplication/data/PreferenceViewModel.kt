package com.example.myapplication.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PreferenceViewModel:ViewModel() {
    var preferred= mutableStateOf(PreferenceUIState())

}