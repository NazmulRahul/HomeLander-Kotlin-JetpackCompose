package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.data.DataViewModel


@Composable
fun ShowHomeScreen(dataViewModel: DataViewModel =viewModel(), modifier:Modifier=Modifier){
    val getData=dataViewModel.stateList.value
    LazyColumn(modifier = modifier.fillMaxWidth()){
        items(getData){
                item->
            Log.d("hello","$item.last")
            Card(modifier = modifier.fillMaxWidth().padding(20.dp)){
                AsyncImage(
                    model = item.image,
                    contentDescription = "Translated description of what the image contains"
                )
                Text(text=item.description,modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall)
                Text(text=item.address,modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall)
                Text(text=item.rent,modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}
