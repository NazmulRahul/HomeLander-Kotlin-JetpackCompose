package com.example.myapplication.screens
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.example.myapplication.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID

@Composable
fun UploadScreen(modifier:Modifier=Modifier){
    Surface(
        color= Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(Color.White)
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
        var description by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var rent by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        Column(modifier.fillMaxSize()){
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
                value = rent,
                onValueChange = { newValue ->
                    rent = newValue
                },
                label = { Text(stringResource(id = R.string.Rent)) }
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { newValue ->
                    phone = newValue
                },
                label = { Text("phone") }
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                singlePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )

            }){
                Text("Pick Single Image")
            }

            AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))

            Button(onClick = {
                uri?.let{
                    Upload(uri=it, context=context,description,rent,address,phone)
                }

            }){
                Text("Upload")

            }
            Button(
                onClick={
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            ){
                Text("HOME")
            }


        }

    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}
fun Upload(uri: Uri, context: Context,description:String,rent:String,address:String,phone:String){
//    val description=houseDataUiState.value.description
//    val rent=houseDataUiState.value.rent
//    val address=houseDataUiState.value.address
    val unique_image_name = UUID.randomUUID()
    val newHome = hashMapOf(
        "phone" to "$phone",
        "description" to "$description",
        "address" to "$address",
        "rent" to "$rent",
        "image" to "https://firebasestorage.googleapis.com/v0/b/androidtest-eb91b.appspot.com/o/images%2F${unique_image_name}.jpg?alt=media&token=00fc5e50-7bc7-463b-975f-eb58daf73b1f"
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

@Preview
@Composable
fun DefaultPreviewOfUploadScreen(){
    UploadScreen()
}