package com.example.myapplication.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.PreferenceViewModel
import com.example.myapplication.data.ProfileViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Preference(preferenceViewModel: PreferenceViewModel= viewModel(),loginViewModel: LoginViewModel=viewModel()){
    var maxPrice by remember {
        mutableStateOf("")
    }
    var minPrice by remember { mutableStateOf("") }
    var minSize by remember { mutableStateOf("") }
    var maxSize by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
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
                        value = minPrice,
                        onValueChange = { newValue ->
                           minPrice= newValue
                        },
                        label = { Text("minimum price in BDT") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value =  maxPrice,
                        onValueChange = { newValue ->
                            maxPrice= newValue
                        },
                        label = { Text("maximum price in BDT") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = location,
                        onValueChange = { newValue ->
                            location= newValue
                        },
                        label = { Text("Location") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = minSize,
                        onValueChange = { newValue ->
                           minSize= newValue
                        },
                        label = { Text("minimum square feet") }
                    )
                    Spacer(Modifier.height(20.dp))
                    OutlinedTextField(
                        value = maxSize,
                        onValueChange = { newValue ->
                           maxSize= newValue
                        },
                        label = { Text("maximum square feet") }
                    )
                    ElevatedButton( onClick = { Upload(
                        loginViewModel.loginUIState.value.email,
                        minPrice,
                        maxPrice,
                        location,
                        minSize,
                        maxSize)},
                        contentPadding = PaddingValues(all = 10.dp),
                        modifier = Modifier
                            .padding(20.dp)
                            .width(120.dp)
                    ) {
                        Text(text="Submit"
                        )
                    }
                }

            }
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
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

fun Upload(email:String,minPrice:String,maxPrice:String,location:String,minSize:String,maxSize:String){
    val myPreference = hashMapOf(
        "email" to "$email",
        "minPrice" to "$minPrice",
        "maxPrice" to "$maxPrice",
        "location" to "$location",
        "minSize" to "$minSize",
        "maxSize" to "$maxSize"
    )

    val db = Firebase.firestore

    db.collection("Preference")
        .add(myPreference)
        .addOnSuccessListener { documentReference ->
            Log.d("newHouseAdded", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("failedHouseAdded", "Error adding document", e)
        }

}