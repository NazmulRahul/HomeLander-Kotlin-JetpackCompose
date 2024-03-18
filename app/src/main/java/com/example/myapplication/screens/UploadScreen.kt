package com.example.myapplication.screens
import android.annotation.SuppressLint
import android.content.Context
import android.health.connect.datatypes.HeightRecord
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.LoginUIEvent
import com.example.myapplication.data.LoginViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UploadScreen(loginViewModel: LoginViewModel= viewModel(), modifier:Modifier=Modifier){

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier)
        }
    ){
        var uri by remember{
            mutableStateOf<Uri?>(null)
        }
        val singlePhotoPicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                uri = it
            }
        )

        val context = LocalContext.current
        var title by remember {
            mutableStateOf("")
        }
        var sqr_ft by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var rent by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        Column( modifier = Modifier
            .fillMaxSize()
            .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = modifier.padding(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White, //Card background color
                    contentColor = Color.Black  //Card content color,e.g.text
                ),elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp))   {
                Column( modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = title,
                        onValueChange = { newValue ->
                            title = newValue
                        },
                        label = { Text("title") }
                    )
                    OutlinedTextField(
                        value = rent,
                        onValueChange = { newValue ->
                            rent = newValue
                        },
                        label = { Text("Price") }
                    )
                    OutlinedTextField(
                        value = sqr_ft,
                        onValueChange = { newValue ->
                            sqr_ft = newValue
                        },
                        label = { Text("sqr-ft") }
                    )
                    OutlinedTextField(
                        value = city,
                        onValueChange = { newValue ->
                            city = newValue
                        },
                        label = { Text("city") }
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newValue ->
                            description = newValue
                        },
                        label = { Text(stringResource(id = R.string.Description)) }
                    )
                    OutlinedTextField(
                        value = address,
                        onValueChange = { newValue ->
                            address = newValue
                        },
                        label = { Text(stringResource(id = R.string.Address)) }
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { newValue ->
                            phone = newValue
                        },
                        label = { Text("phone") }
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Row{
                        Column(){
                            IconButton(onClick = {
                                singlePhotoPicker.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )

                            }) {
                                Row {
                                    Icon(Icons.Filled.CameraAlt, contentDescription = "Camera",
                                        Modifier.size(60.dp)
                                    )
                                }

                            }
                        }
                        Spacer(modifier = modifier.width(60.dp))
                        Column{
                            IconButton(onClick = { uri?.let{
                                Upload(uri=it, context=context,loginViewModel.loginUIState.value.email,title,city,sqr_ft,description,rent,address,phone)
                            }}) {
                                Icon(
                                    Icons.Filled.CloudUpload, contentDescription = "Upload",
                                    Modifier.size(45.dp)
                                )
                            }

                        }
                    }
                    AsyncImage(model = uri, contentDescription = null)

            }


            }


        }

    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}
fun Upload(uri: Uri, context: Context,email:String,title:String,city:String,sqr_ft:String,description:String,rent:String,address:String,phone:String){
//    val description=houseDataUiState.value.description
//    val rent=houseDataUiState.value.rent
//    val address=houseDataUiState.value.address
    val unique_image_name = UUID.randomUUID()
    val newHome = hashMapOf(
        "phone" to "$phone",
        "description" to "$description",
        "address" to "$address",
        "rent" to "$rent",
        "title" to "$title",
        "city" to "$city",
        "sqr_ft" to "$sqr_ft",
        "image" to "https://firebasestorage.googleapis.com/v0/b/androidtest-eb91b.appspot.com/o/images%2F${unique_image_name}.jpg?alt=media&token=00fc5e50-7bc7-463b-975f-eb58daf73b1f",
        "email" to "$email"
    )
    val storage= Firebase.storage
    val db = Firebase.firestore

    db.collection("house")
        .add(newHome)
        .addOnSuccessListener { documentReference ->
            Log.d("newHouseAdded", "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("failedHouseAdded", "Error adding document", e)
        }


    var storageRef = storage.reference
    var spaceRef: StorageReference


    spaceRef = storageRef.child("images/${unique_image_name}.jpg")

    val byteArray: ByteArray? = context.contentResolver
        .openInputStream(uri)
        ?.use { it.readBytes() }

    byteArray?.let{

        var uploadTask = spaceRef.putBytes(byteArray)
        uploadTask.addOnFailureListener {
            Toast.makeText(
                context,
                "upload failed",
                Toast.LENGTH_SHORT
            ).show()
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            Toast.makeText(
                context,
                "upload successed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier,loginViewModel: LoginViewModel = viewModel()) {
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

@Preview
@Composable
fun DefaultPreviewOfUploadScreen(){
    UploadScreen()
}