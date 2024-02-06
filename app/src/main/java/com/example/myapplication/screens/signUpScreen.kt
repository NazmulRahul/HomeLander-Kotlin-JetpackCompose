package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.components.ButtonComponent
import com.example.myapplication.components.ClickableLoginTextComponent
import com.example.myapplication.components.HeadingComponent
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.components.NormalComponent
import com.example.myapplication.components.PasswordTextFieldComponent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.UIEvent
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen

@Composable
fun SignUpScreen(loginViewModel: LoginViewModel= viewModel()){
Surface(
    color= Color.White,
    modifier = Modifier
        .fillMaxSize()
        .padding(28.dp)
        .background(Color.White)
    ){
        Column(modifier=Modifier.fillMaxSize()){
            NormalComponent (value= stringResource(id = R.string.hello))
            HeadingComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier=Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource(id = R.drawable.profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                }
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                }
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                }
            )
            Spacer(modifier=Modifier.height(60.dp))
            ButtonComponent(value = stringResource(id = R.string.register), onButtonClicked = {
                loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
//                AppRouter.navigateTo(Screen.LogInScreen)
            })
            Spacer(modifier=Modifier.height(40.dp))
            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                AppRouter.navigateTo(Screen.LogInScreen)
            })

        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}