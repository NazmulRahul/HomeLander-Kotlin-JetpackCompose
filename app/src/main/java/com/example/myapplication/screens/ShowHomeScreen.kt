package com.example.myapplication.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.components.MyTextFieldComponent
import com.example.myapplication.data.AboutHome
import com.example.myapplication.data.DataViewModel
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.example.myapplication.ui.theme.componentShapes


@Composable
fun ShowHomeScreen(dataViewModel: DataViewModel = viewModel()) {
    val getData=dataViewModel.stateList.value

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Scaffold(
            topBar = {
                AppBar(modifier = Modifier)
            },


        ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(6.dp))
            CardButtons()

            LazyColumn(modifier=Modifier.background(color = Color(0xffFAFAFA))) {


                items(getData) {
                    ApartmentItem(
                        homeDetails = it
//                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

        }

        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }

}

@Composable
fun CardButtons(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        MyCard(event={AppRouter.navigateTo(Screen.UploadScreen)},text = "Nearby", imageVector = Icons.Default.LocationOn)
        MyCard(event={AppRouter.navigateTo(Screen.UploadScreen)},text = "Add", imageVector = Icons.Default.Add)
        MyCard(event={AppRouter.navigateTo(Screen.FilterScreen)},text = "Filter", imageVector = Icons.Default.FilterList)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(event:()->Unit,text:String, imageVector: ImageVector){
    Card(
        modifier = Modifier.clickable {  event.invoke()},
        onClick={event.invoke()},
        colors = CardDefaults.cardColors(
            containerColor = Color.White, //Card background color
            contentColor = Color.Black  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.size(110.dp)
        ) {
            Text(text = text)
            Icon(imageVector = imageVector, contentDescription = null)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier,loginViewModel: LoginViewModel=viewModel()) {
    CenterAlignedTopAppBar(
        title = {
                Text(
                    text = "HomeLander",
                    style = MaterialTheme.typography.headlineLarge
                )
        },

        navigationIcon = {
                         IconButton(onClick = { AppRouter.navigateTo(Screen.HomeScreen) }) {
                             Icon(
                                 imageVector = Icons.Default.Home,
                                 contentDescription = null,
                                 modifier = Modifier.size(28.dp)
                             )
                         }
        },

        actions = {
                  IconButton(onClick = {loginViewModel.onEvent(LoginUIEvent.LogoutButtonClicked)}) {
                      Icon(
                          imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                          contentDescription = null,
                          modifier = Modifier.size(28.dp)
                      )
                  }
        },
        modifier = modifier.background(Color.Cyan)
    )
}

@Composable
fun ApartmentItem(
    homeDetails: AboutHome,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = ""
    )
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),shape = RoundedCornerShape(30.dp), elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    )) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = Color.White)
                .fillMaxWidth(),

        ) {
            AsyncImage(
                model = homeDetails.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .size(400.dp)
                    .fillMaxSize()
            )
//            Text(
//                text="Price: ${homeDetails.rent}",
//                style=MaterialTheme.typography.bodyLarge,
//                color=Color.DarkGray
//            )
            SingleDataRow(imageVector = null, description = homeDetails.title)
            SingleDataRow(imageVector = Icons.Default.LocationOn, description = homeDetails.city)
            DoubleDataRow(headingStart = "Price: ", descFirst = homeDetails.price, headingEnd = "Sq. Ft: ", descEnd = homeDetails.sqr_ft)

//            Spacer(modifier = modifier.weight(1f))
            ExpandedButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )

            if (expanded) {
                ApartmentDetails(
                    name = homeDetails.description,
                    description = homeDetails.address,
                    trainer = homeDetails.email,
                    phone=homeDetails.phone,
                    modifier.padding(0.dp)
                )
            }
        }
    }
}

@Composable
fun SingleDataRow(imageVector: ImageVector?, description: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(imageVector ==null){
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        } else {
            Icon(imageVector = imageVector, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))

            Text(text = description,
                color=Color(0xff2e8bc0),
                fontWeight = FontWeight.Bold
                )
        }

    }
}

@Composable
fun DoubleDataRow(headingStart: String, descFirst:String, headingEnd:String, descEnd: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
//        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        showData(heading = headingStart, description = descFirst)
        Spacer(modifier = Modifier.width(80.dp))
        showData(heading = headingEnd, description = descEnd)
    }
}

@Composable
fun showData(
    heading: String, description: String
){
    Row {
        Text(text = heading, fontWeight = FontWeight.Bold)
        Text(text = description)
    }
}

@Composable
fun ExpandedButton(
    expanded: Boolean = false,
    onClick: () -> Unit = {},
    modifier:Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun ApartmentDetails(
    name: String,
    description: String,
    trainer: String,
    phone:String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(10.dp)) {
        DescriptionRow(
            heading = "Description: ",
            details = name,
            modifier = Modifier.weight(2f)
        )

        DescriptionRow(
            heading = "Address: ",
            details = description,
            modifier = Modifier.weight(2f)
        )

        DescriptionRow(
            heading = "Price: ",
            details = trainer,
            modifier = Modifier.weight(2f)
        )
        DescriptionRow(
            heading = "Phone:",
            details = phone,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun DescriptionRow(
    heading: String,
    details: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = heading,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )

        //Spacer(modifier = modifier)

        Text(text = details,
            style = MaterialTheme.typography.bodyLarge)
    }
}


@Preview
@Composable
fun PreviewHome(){
    ShowHomeScreen()
}
//@Preview
//@Composable
//fun PreviewCard(){
//    ScrollableListTheme {
//        ApartmentItem(
//            homeDetails = HomeDetails(
//            image = R.drawable.bulbasaur,
//            description = R.string.pkmn1,
//            rent = R.string.dtlsPkmn1,
//            address = R.string.trainer1
//        ),modifier = Modifier.padding(8.dp))
//    }
//}

