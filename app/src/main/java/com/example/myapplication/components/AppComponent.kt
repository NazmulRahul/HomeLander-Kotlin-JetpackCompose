package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

@Composable
fun NormalComponent(value:String){
    Text(
        text=value,
        modifier=Modifier.fillMaxWidth().heightIn(min=80.dp),
        style= androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    )
}