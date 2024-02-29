package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.app.HouseRentingApp
import com.example.myapplication.screens.CenterAlignedTopAppBarExample
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LogInScreen
import com.example.myapplication.screens.ShowHomeScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.UploadScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseRentingApp()
//    LogInScreen()
//HomeScreen()
//            CenterAlignedTopAppBarExample()
//        SignUpScreen()
//            ShowHomeScreen()
//            UploadScreen()
        }
    }
}

