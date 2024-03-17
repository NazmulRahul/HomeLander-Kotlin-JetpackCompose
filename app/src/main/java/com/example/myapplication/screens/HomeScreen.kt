package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.HeadingComponent
import com.example.myapplication.components.SmallButtonComponent
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.SignupViewModel
import com.example.myapplication.data.SignupUIEvent
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun HomeScreen(loginViewModel: LoginViewModel= viewModel()){
    Surface(
        color= Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Box(modifier=Modifier.fillMaxSize()){

    }
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(28.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter= painterResource(id = R.drawable.house_logo_png_21),
                contentDescription=""
            )
//            HeadingComponent(value = "HOME SCREEN")
            Spacer(modifier=Modifier.height(60.dp))
            ButtonComponent(value = "MyProfile", onButtonClicked = {
                AppRouter.navigateTo(Screen.Profile)
            })
            Spacer(modifier=Modifier.height(20.dp))
            ButtonComponent(value = "Browse Home", onButtonClicked = {
                AppRouter.navigateTo(Screen.ShowHomeScreen)
            })
            Spacer(modifier=Modifier.height(20.dp))

            ButtonComponent(value = "Add Home", onButtonClicked = {
                AppRouter.navigateTo(Screen.UploadScreen)
            })
            Spacer(modifier=Modifier.height(60.dp))
            SmallButtonComponent(value = "Suggested", onButtonClicked = {
                AppRouter.navigateTo(Screen.Suggested)
            })
            SmallButtonComponent(value = "Logout", onButtonClicked = {
                loginViewModel.onEvent(LoginUIEvent.LogoutButtonClicked)
            })
        }
    }
//    SystemBackButtonHandler {
//        AppRouter.navigateTo(Screen.LogInScreen)
//    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    MyApplicationTheme {
        HomeScreen()
    }
}