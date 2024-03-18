package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.AboutHome
import com.example.myapplication.data.DataViewModel
import com.example.myapplication.data.FilteredDataViewModel
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun MyListings(dataViewModel: DataViewModel=viewModel(),loginViewModel: LoginViewModel=viewModel()){
    var myList=dataViewModel.stateList.value
    var newList=mutableListOf(AboutHome())
    for(l in myList){
        Log.d("currEmail","${l.email}")
        if(l.email==loginViewModel.loginUIState.value.email){
            newList.add(l)
        }
    }
    Scaffold(
        topBar = {
            AppBar(modifier = Modifier)
        },


        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Log.d("listings","$newList")
            LazyColumn(modifier=Modifier.background(color = Color(0xffFAFAFA))) {
                items(newList) {
                    ApartmentItem(
                        homeDetails = it
//                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }

    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}