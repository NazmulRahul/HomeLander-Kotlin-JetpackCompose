package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.screens.LogInScreen
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID
@Composable
fun SinglePhotoPicker(){
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


    Column{
        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )

        }){
            Text("Pick Single Image")
        }

//        AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))

        Button(onClick = {
            uri?.let{
                uploadToStorage(uri=it, context=context, type="image")
            }

        }){
            Text("Upload")
        }

    }
}

fun uploadToStorage(uri: Uri, context: Context, type: String) {
    val storage = Firebase.storage

    // Create a storage reference from our app
    var storageRef = storage.reference

    val unique_image_name = UUID.randomUUID()
    var spaceRef: StorageReference

    if (type == "image") {
        spaceRef = storageRef.child("images/$unique_image_name.jpg")
    } else {
        spaceRef = storageRef.child("videos/$unique_image_name.mp4")
    }

    val byteArray: ByteArray? = context.contentResolver
        .openInputStream(uri)
        ?.use { it.readBytes() }

    byteArray?.let {

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
fun LoginScreenPreview(){
    SinglePhotoPicker()
}
