package com.example.myapplication.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.AboutHome
import com.example.myapplication.data.DataViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler


@Composable
fun ShowHomeScreen(dataViewModel: DataViewModel = viewModel()) {
    val getData=dataViewModel.stateList.value
    Scaffold(
        topBar = {
            AppBar(modifier = Modifier)
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(getData) {
                ApartmentItem(
                    homeDetails = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        },
        modifier = modifier
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
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = homeDetails.image,
                contentDescription = null,
                modifier = modifier
                    .size(400.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

//            Spacer(modifier = modifier.weight(1f))
            ExpandedButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )

            if (expanded) {
                ApartmentDetails(
                    name = homeDetails.description,
                    description = homeDetails.address,
                    trainer = homeDetails.rent
                )
            }
        }
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
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DescriptionRow(
            heading = "Description",
            details = name,
            modifier = Modifier.weight(0.5f)
        )

        DescriptionRow(
            heading = "Address:",
            details = description,
            modifier = Modifier.weight(0.5f)
        )

        DescriptionRow(
            heading = "Rent:",
            details = trainer,
            modifier = Modifier.weight(0.5f)
        )
    }
}

@Composable
fun DescriptionRow(
    heading: String,
    details: String,
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = heading,
            fontWeight = FontWeight.Bold
        )

        //Spacer(modifier = modifier)

        Text(text = details)
    }
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

