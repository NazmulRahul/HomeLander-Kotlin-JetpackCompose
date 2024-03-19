package com.example.myapplication.screens

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.AboutHome
import com.example.myapplication.data.DataViewModel
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.PreferenceUIState
import com.example.myapplication.data.PreferenceViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@Composable
fun Suggested(preferenceViewModel: PreferenceViewModel=viewModel(),dataViewModel:DataViewModel= viewModel(),loginViewModel: LoginViewModel=viewModel()){
    var myList=dataViewModel.stateList.value
    var newList=mutableListOf(AboutHome())
    var preference=preferenceViewModel.preferred
    var tem= PreferenceUIState()
    for(p in preference.value){
        Log.d("ForLoop","$p")
        if(p.email==loginViewModel.loginUIState.value.email){
            tem=p;
        }
    }
    for(l in myList){
        Log.d("ForLoop","$l")
        if(l.price.length==0 || l.sqr_ft.length==0 || tem.maxPrice.length==0 || tem.minPrice.length==0 || tem.minSize.length==0 || tem.maxSize.length==0){
            continue
        }
        if(l.price.toInt()>=tem.minPrice.toInt() && l.price.toInt()<=tem.maxPrice.toInt() && l.city==tem.location && l.sqr_ft.toInt()>=tem.minSize.toInt() && l.sqr_ft.toInt()<=tem.maxSize.toInt()){
            newList.add(l)
        }
    }
    Log.d("TemPorary","$tem")
    Log.d("Suggested","$newList")
     var NewList=newList.filter{it.image.length>0}
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
            LazyColumn(modifier= Modifier.background(color = Color(0xffFAFAFA))) {
                items(NewList) {
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
