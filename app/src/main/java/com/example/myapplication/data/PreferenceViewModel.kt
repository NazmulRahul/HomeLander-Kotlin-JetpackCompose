package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PreferenceViewModel:ViewModel() {
    var preferred= mutableStateOf(mutableListOf(PreferenceUIState()))
    var myList= mutableStateOf(PreferenceUIState())
    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            preferred.value= getPreferredFromFirestore()
        }
    }
}

suspend fun getPreferredFromFirestore(): MutableList<PreferenceUIState> {
    val db= FirebaseFirestore.getInstance()
    var about= AboutHome()
    var aboutList= mutableListOf<PreferenceUIState>()
    try{
        db.collection("house")
            .get()
            .await().map{
                Log.d("hello","$it")
                val result= it.toObject(PreferenceUIState::class.java)
                aboutList.add(result)
            }
    }catch (e: FirebaseFirestoreException){
        Log.d("error","getdata")
    }
    return aboutList
}