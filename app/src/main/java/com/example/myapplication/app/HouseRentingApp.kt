package com.example.myapplication.app

import Welcome
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.screens.FilterScreen
import com.example.myapplication.screens.FilteredScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LogInScreen
import com.example.myapplication.screens.MyListings
import com.example.myapplication.screens.NearbyScreen
import com.example.myapplication.screens.Preference
import com.example.myapplication.screens.Profile
import com.example.myapplication.screens.ShowHomeScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.Suggested
import com.example.myapplication.screens.UploadScreen
import java.util.logging.Filter

@Composable
fun HouseRentingApp(){
    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.LogInScreen -> {
                    LogInScreen()
                }
                is Screen.HomeScreen->{
                    HomeScreen()
                }
                is Screen.ShowHomeScreen->{
                    ShowHomeScreen()
                }
                is Screen.UploadScreen->{
                    UploadScreen()
                }
                is Screen.Welcome->{
                    Welcome()
                }
                is Screen.Profile->{
                    Profile()
                }
                is Screen.Preference->{
                    Preference()
                }
                is Screen.Suggested->{
                    Suggested()
                }
                is Screen.FilterScreen->{
                    FilterScreen()
                }
                is Screen.MyListings->{
                    MyListings()
                }
                is Screen.FilteredScreen->{
                    FilteredScreen()
                }
                is Screen.NearbyScreen->{
                    NearbyScreen()
                }
            }
        }
    }
}