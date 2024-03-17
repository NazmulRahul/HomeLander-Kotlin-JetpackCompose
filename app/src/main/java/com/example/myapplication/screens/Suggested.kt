package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.PreferenceViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun Suggested(preferenceViewModel: PreferenceViewModel=viewModel()){
    Column {
        Text(
            text=preferenceViewModel.preferred.value.city,
            fontSize = 20.sp
        )
        Text(
            text=preferenceViewModel.preferred.value.rent,
            fontSize = 20.sp
        )
        Text(
            text=preferenceViewModel.preferred.value.sizeMax,
            fontSize = 20.sp
        )
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.Profile)
    }
}
