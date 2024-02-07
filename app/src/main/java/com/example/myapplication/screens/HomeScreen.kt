package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingComponent
import com.example.myapplication.data.SignupViewModel
import com.example.myapplication.data.SignupUIEvent

@Composable
fun HomeScreen(signupViewModel: SignupViewModel = viewModel()){
    Surface(
        color= Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(Color.White)
    ){
        Column(modifier=Modifier.fillMaxSize()){
            HeadingComponent(value = "HOME SCREEN")
            ButtonComponent(value = "Logout", onButtonClicked = {
                signupViewModel.onEvent(SignupUIEvent.LogoutButtonClicked)
            })
        }
    }
}