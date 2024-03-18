package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun LogInScreen(loginViewModel: LoginViewModel= viewModel(),modifier:Modifier=Modifier){
    Column(modifier=Modifier.fillMaxSize()) {
            Image(
                painter= painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier= Modifier
                    .fillMaxWidth()
                    .size(400.dp)
                    .clip(CircleShape)
            )
//            NormalComponent(value = stringResource(id = R.string.hello))
//            HeadingComponent(value = stringResource(id = R.string.welcome))
//            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                }
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                }
            )
            Spacer(modifier=Modifier.height(60.dp))
            ButtonComponent(value = stringResource(id = R.string.login),onButtonClicked = {
                loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
            })
            Spacer(modifier=Modifier.height(12.dp))
            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                AppRouter.navigateTo(Screen.SignUpScreen)
            })
        }
    }
//    SystemBackButtonHandler {
//        AppRouter.navigateTo(Screen.SignUpScreen)
//    }

@Preview
@Composable
fun LoginScreenPreview(){
    MyApplicationTheme {
        LogInScreen()
    }
}