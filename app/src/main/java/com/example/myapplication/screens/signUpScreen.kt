package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import com.example.myapplication.data.SignupViewModel
import com.example.myapplication.data.SignupUIEvent
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel= viewModel()){
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter= painterResource(id = R.drawable.logo1),
                contentDescription = null,
                modifier=Modifier.fillMaxWidth()
            )
            NormalComponent (value= stringResource(id = R.string.hello))
            HeadingComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier=Modifier.height(20.dp))

            Card( modifier = Modifier.padding(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, //Card background color
                    contentColor = Color.Black  //Card content color,e.g.text
                ),elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp)){
                Column(Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        }
                    )
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                        }
                    )
                    MyTextFieldComponent(
                        labelValue ="Location",
                        painterResource(id = R.drawable.profile),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.LocationChanged(it))
                        }
                    )
                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.email),
                        painterResource(id = R.drawable.message),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                        }
                    )
                    PasswordTextFieldComponent(
                        labelValue = stringResource(id = R.string.password),
                        painterResource(id = R.drawable.ic_lock),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                        }
                    )
                    Spacer(modifier=Modifier.height(60.dp))
                    ButtonComponent(value = stringResource(id = R.string.register), onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)

                    })
                    Spacer(modifier=Modifier.height(12.dp))
                    ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                        AppRouter.navigateTo(Screen.LogInScreen)
                    })
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }

        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewOfSignUpScreen(){
    MyApplicationTheme(darkTheme = false){
        SignUpScreen()
    }
}