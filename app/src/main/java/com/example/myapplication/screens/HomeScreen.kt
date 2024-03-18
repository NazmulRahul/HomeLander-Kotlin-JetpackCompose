package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme


@Composable
fun HomeScreen(loginViewModel: LoginViewModel=viewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HeadingStuffs()

        CardStuff(
            {AppRouter.navigateTo(Screen.Profile)},
            {AppRouter.navigateTo(Screen.Suggested)},
            imageVectorStart = Icons.Default.Person,
            textStart = "Profile",
            imageVectorEnd = Icons.Default.List,
            textEnd = "Suggested"
        )

        CardStuff(
            {AppRouter.navigateTo(Screen.ShowHomeScreen)},
            {AppRouter.navigateTo(Screen.FilterScreen)},
            imageVectorStart = Icons.Default.BrowseGallery,
            textStart = "Browse",
            imageVectorEnd = Icons.Default.FilterList,
            textEnd = "Filter"
        )

        CardStuff(
            {AppRouter.navigateTo(Screen.MyListings)},
            {loginViewModel.onEvent(LoginUIEvent.LogoutButtonClicked)},
            imageVectorStart = Icons.Default.Home,
            textStart = "My Listings",
            imageVectorEnd = Icons.AutoMirrored.Filled.ExitToApp,
            textEnd="Logout"

        )
    }
}

@Composable
fun CardStuff(
    event1:()->Unit,
    event2:()->Unit,
    imageVectorStart: ImageVector,
    textStart:String,
    imageVectorEnd: ImageVector,
    textEnd:String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ShowCard(event1, imageVector = imageVectorStart, text = textStart)
        ShowCard(event2,imageVector = imageVectorEnd, text = textEnd)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCard(event:()->Unit,imageVector: ImageVector, text: String) {
    Card(
        modifier = Modifier
            .size(168.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {  },
        onClick={event.invoke()}
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )

            Text(text = text)
        }
    }
}

@Composable
fun HeadingStuffs(){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        TitleAndPunchline()

        Image(painter = painterResource(id = R.drawable.icon), contentDescription = null)
    }
}

@Composable
fun TitleAndPunchline(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
        Text(text = "Where you land for home")
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    MyApplicationTheme {
        HomeScreen()
    }
}