package com.example.myapplication.data

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel: ViewModel() {
    var stateList= mutableStateOf(mutableListOf(ProfileUiState()))
    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            stateList.value= getProfileFromFirestore()
        }
    }
}
suspend fun getProfileFromFirestore(): MutableList<ProfileUiState> {
    val db= FirebaseFirestore.getInstance()
    var aboutList= mutableListOf<ProfileUiState>()
    try{
        db.collection("profile")
            .get()
            .await().map{
                Log.d("hello","$it")
                val result= it.toObject(ProfileUiState::class.java)
                aboutList.add(result)
            }
    }catch (e: FirebaseFirestoreException){
        Log.d("error","getdata")
    }
    return aboutList
}