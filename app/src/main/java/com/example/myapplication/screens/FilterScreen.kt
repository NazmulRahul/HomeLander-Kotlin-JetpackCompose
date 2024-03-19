package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.FilteredDataViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.android.play.integrity.internal.f

@Composable
fun FilterScreen(filteredDataViewModel: FilteredDataViewModel=viewModel()){
    var city by remember { mutableStateOf("") }
    var maxRent by remember {
        mutableStateOf("")
    }
    var minRent by remember { mutableStateOf("") }
    var sizeMin by remember { mutableStateOf("") }
    var sizeMax by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            AppBar(modifier = Modifier)
        }
    ){
        Column( modifier = Modifier
            .fillMaxSize()
            .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier.padding(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, //Card background color
                    contentColor = Color.Black  //Card content color,e.g.text
                ),elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp))
            {
                Column(Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value =  minRent,
                        onValueChange = { newValue ->
                            minRent= newValue
                            filteredDataViewModel.filteredList.value.minRent=minRent
                        },
                        label = { Text("minimum rent in BDT") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value =  maxRent,
                        onValueChange = { newValue ->
                            maxRent= newValue
                            filteredDataViewModel.filteredList.value.maxRent=maxRent
                        },
                        label = { Text("maximum rent in BDT") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = city,
                        onValueChange = { newValue ->
                            city= newValue
                            filteredDataViewModel.filteredList.value.location=city
                        },
                        label = { Text("City") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value =  sizeMin,
                        onValueChange = { newValue ->
                            sizeMin= newValue
                            filteredDataViewModel.filteredList.value.minSize=sizeMin
                        },
                        label = { Text("Minimum size in sqft") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = sizeMax,
                        onValueChange = { newValue ->
                            sizeMax= newValue
                            filteredDataViewModel.filteredList.value.maxSize=sizeMax
                        },
                        label = { Text("Maximum size in sqft") }
                    )
                    ElevatedButton( onClick = {AppRouter.navigateTo(Screen.FilteredScreen) },
                        contentPadding = PaddingValues(all = 10.dp),
                        modifier = Modifier
                            .padding(20.dp)
                            .width(120.dp)
                    ) {
                        Text(text="Filter"
                        )
                    }
                }

           }
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.ShowHomeScreen)
    }
}
