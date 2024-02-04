package com.example.myapplication.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.components.NormalComponent

@Composable
fun SignUpScreen(){
Surface(
    color= Color.White,
    modifier = Modifier.fillMaxSize()
    ){
        NormalComponent (value= stringResource(id = R.string.hello))
    }
}

@Preview
@Composable

fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}