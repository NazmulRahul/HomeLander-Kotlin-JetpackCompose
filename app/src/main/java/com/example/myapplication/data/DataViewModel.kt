package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel:ViewModel(){
    var stateList= mutableStateOf(mutableListOf(AboutHome()))
    var state= mutableStateOf(AboutHome())
    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            stateList.value= getDataFromFirestore()
        }
    }
}
suspend fun getDataFromFirestore(): MutableList<AboutHome> {
    val db=FirebaseFirestore.getInstance()
    var about= AboutHome()
    var aboutList= mutableListOf<AboutHome>()
    try{
        db.collection("house")
            .get()
            .await().map{
               Log.d("hello","$it")
                val result= it.toObject(AboutHome::class.java)
                aboutList.add(result)
            }
    }catch (e: FirebaseFirestoreException){
        Log.d("error","getdata")
    }
    return aboutList
}
