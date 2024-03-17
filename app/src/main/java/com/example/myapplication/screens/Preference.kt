package com.example.myapplication.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.PreferenceViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Preference(preferenceViewModel: PreferenceViewModel= viewModel()){
    var city by remember { mutableStateOf("") }
    var rent by remember { mutableStateOf("") }
    var sizeMin by remember { mutableStateOf("") }
    var sizeMax by remember { mutableStateOf("") }
      Scaffold(
        topBar = {
            ProfileTopBar()
        }
    ){
        Column ( horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)){
            OutlinedTextField(
                value =  city,
                onValueChange = { newValue ->
                    city= newValue
                    preferenceViewModel.preferred.value.city=city
                },
                label = { Text("city") }
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value =  rent,
                onValueChange = { newValue ->
                    rent= newValue
                    preferenceViewModel.preferred.value.rent=rent
                },
                label = { Text("rent") }
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value =  sizeMin,
                onValueChange = { newValue ->
                   sizeMin= newValue
                    preferenceViewModel.preferred.value.sizeMin=sizeMin
                },
                label = { Text("Minimum size sqft") }
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = sizeMax,
                onValueChange = { newValue ->
                   sizeMax= newValue
                    preferenceViewModel.preferred.value.sizeMax=sizeMax
                },
                label = { Text("Maximum size sqft") }
            )
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.Profile)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(

) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Profile")
        },
        navigationIcon = {
            IconButton(
                onClick = {AppRouter.navigateTo(Screen.HomeScreen)}
            ){
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ){
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
            }
        }
    )
}