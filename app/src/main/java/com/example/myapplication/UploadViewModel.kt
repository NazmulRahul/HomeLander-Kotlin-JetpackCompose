package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.util.UUID


class UploadViewModel: ViewModel() {
    var houseDataUiState = mutableStateOf(HouseDataUiState())
    fun onEvent(event: HouseDataUiEvent) {
        when (event) {
            is HouseDataUiEvent.DescriptionChanged -> {
                houseDataUiState.value = houseDataUiState.value.copy(
                    description = event.description
                )
            }

            is HouseDataUiEvent.AddressChanged -> {
                houseDataUiState.value = houseDataUiState.value.copy(
                    address = event.address
                )
            }

            is HouseDataUiEvent.RentChanged -> {
                houseDataUiState.value = houseDataUiState.value.copy(
                    description = event.rent
                )
            }

            is HouseDataUiEvent.ImageChanged -> {
                houseDataUiState.value = houseDataUiState.value.copy(
                    description = event.image
                )
            }
            is HouseDataUiEvent.UploadButtonClicked->{

            }

        }
    }
    fun Upload(uri: Uri, context: Context){
        val description=houseDataUiState.value.description
        val rent=houseDataUiState.value.rent
        val address=houseDataUiState.value.address
        val unique_image_name = UUID.randomUUID()
        val newHome = hashMapOf(
            "description" to "$description",
            "address" to "$address",
            "rent" to "$rent",
            "image" to "https://firebasestorage.googleapis.com/v0/b/androidtest-eb91b.appspot.com/o/images%2${unique_image_name}.jpg?alt=media&token=00fc5e50-7bc7-463b-975f-eb58daf73b1f"
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


        spaceRef = storageRef.child("images/$unique_image_name.jpg")

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
}