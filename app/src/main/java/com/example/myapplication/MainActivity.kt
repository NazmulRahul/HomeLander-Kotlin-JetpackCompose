package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.app.HouseRentingApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           HouseRentingApp()
//        SinglePhotoPicker()
//            ShowData()
//            UploadScreen()
        }


    }
}



