package com.example.myapplication.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.screens.SignUpScreen

@Composable
fun HouseRentingApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color= Color.White
    ){
        Crossfade(targetState = AppRouter.currentScreen) {
            currentState->
            when(currentState.value){
                is Screen.SignUpScreen->{
                    SignUpScreen()
                }
            }
        }
    }
}