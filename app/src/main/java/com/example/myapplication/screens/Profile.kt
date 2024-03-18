package com.example.myapplication.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.data.ProfileUiState
import com.example.myapplication.data.ProfileViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.ui.theme.MyApplicationTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(loginViewModel: LoginViewModel = viewModel(), profileViewModel: ProfileViewModel =viewModel()) {
    val email=loginViewModel.loginUIState.value.email
    val profiles=profileViewModel.stateList
    var profile= mutableStateOf(ProfileUiState())
    for(p in profiles.value){
        if(p.Email==email){
            profile.value=p
        }
    }
    Scaffold(
        topBar = {
            ProfileTopBar()
        }
    ) { it ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            ) {
                Detail(
                    imageVector = Icons.Default.Person,
                    headText = "Name",
                    descriptText = "${profile.value.Fname} ${profile.value.Lname}"
                )

                giveSpace()

                Detail(
                    imageVector = Icons.Default.Email,
                    headText = "Email Id",
                    descriptText = "${profile.value.Email}"
                )

                giveSpace()

                Detail(
                    imageVector = Icons.Default.Phone,
                    headText = "Contact No.",
                    descriptText = "012345678"
                )

                Spacer(modifier = Modifier.height(64.dp))

                CardStuffs()
            }

        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}

@Composable
fun giveSpace(){
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CardStuffs() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MakeCards(event={AppRouter.navigateTo(Screen.Preference)},text = "Preferences", imageVector = Icons.Default.Add)
//        Spacer(modifier = Modifier.width(20.dp))
        MakeCards(event={AppRouter.navigateTo(Screen.MyListings)},text = "Listings", imageVector = Icons.Default.Home)
    }
}

@Composable
fun MakeCards(event:()->Unit,text:String, imageVector: ImageVector){
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable {event.invoke() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text)
            Icon(imageVector = imageVector, contentDescription =null )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
loginViewModel: LoginViewModel=viewModel()
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
                onClick = {loginViewModel.onEvent(LoginUIEvent.LogoutButtonClicked)}
            ){
                Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null)
            }
        }
    )
}

@Composable
fun Detail(imageVector: ImageVector, headText: String, descriptText: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = imageVector, contentDescription = null)

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = headText,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = descriptText
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePagePreview() {
    MyApplicationTheme {
        Profile()
    }
}


