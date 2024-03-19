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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.AboutHome
import com.example.myapplication.data.DataViewModel
import com.example.myapplication.data.FilteredDataViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.common.primitives.UnsignedBytes.toInt

@Composable
fun FilteredScreen(dataViewModel:DataViewModel=viewModel(),filteredDataViewModel: FilteredDataViewModel=viewModel()){
    var myList=dataViewModel.stateList.value
    var newList=mutableListOf(AboutHome())
    for(l in myList){
        Log.d("ForLoop","$l")
        if(l.price.length==0 || l.sqr_ft.length==0||filteredDataViewModel.filteredList.value.minRent.length==0||
            filteredDataViewModel.filteredList.value.maxRent.length==0||filteredDataViewModel.filteredList.value.minSize.length==0
            ||filteredDataViewModel.filteredList.value.maxSize.length==0
            ){
            continue
        }
        if(l.price.toInt()>=filteredDataViewModel.filteredList.value.minRent.toInt() && l.price.toInt()<=filteredDataViewModel.filteredList.value.maxRent.toInt() && l.city==filteredDataViewModel.filteredList.value.location && l.sqr_ft.toInt()>=filteredDataViewModel.filteredList.value.minSize.toInt() && l.sqr_ft.toInt()<=filteredDataViewModel.filteredList.value.maxSize.toInt()){
            newList.add(l)
        }
    }
    Log.d("newLIST","$newList")
    var NewList=newList.filter { it.image.length>0 }
    Log.d("NEWLIST","$NewList")
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
            Log.d("listings","$NewList")
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
        AppRouter.navigateTo(Screen.ShowHomeScreen)
    }
}