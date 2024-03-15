package com.example.myapplication.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.ProfileUiState
import com.example.myapplication.data.ProfileViewModel


@Composable
fun Profile(loginViewModel: LoginViewModel= viewModel(), profileViewModel: ProfileViewModel=viewModel()){
    val email=loginViewModel.loginUIState.value.email
    val profiles=profileViewModel.stateList
    var profile= mutableStateOf(ProfileUiState())
    for(p in profiles.value){
        if(p.Email==email){
            profile.value=p
        }
    }
    Column( modifier = Modifier.fillMaxSize()){
        Image(painterResource(id = R.drawable.profile),
                contentDescription=null
            )
        Text(
            text=profile.value.Fname,
            fontSize=20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text=profile.value.Lname,
            fontSize=20.sp
        )

    }
}

