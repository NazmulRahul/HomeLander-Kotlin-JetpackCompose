package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.app.HouseRentingApp
import com.example.myapplication.screens.CenterAlignedTopAppBarExample
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LogInScreen
import com.example.myapplication.screens.ShowHomeScreen
import com.example.myapplication.screens.SignUpScreen
import com.example.myapplication.screens.UploadScreen
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
                ){
                    HouseRentingApp()
                }

            }
//            HouseRentingApp()
//    LogInScreen()
//HomeScreen()
//            CenterAlignedTopAppBarExample()
//        SignUpScreen()
//            ShowHomeScreen()
//            UploadScreen()
        }
    }
}

