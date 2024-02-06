package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.navigation.AppRouter
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var registrationUIState= mutableStateOf(RegistrationUIState())
    fun onEvent(event:UIEvent){
        when(event){
            is UIEvent.FirstNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    firstName=event.firstName
                )
                printState()
            }
            is UIEvent.LastNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is UIEvent.EmailChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is UIEvent.PasswordChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is UIEvent.RegisterButtonClicked->{
                signUp()
                printState()
            }
        }
    }
    private fun signUp(){
        Log.d(TAG, "Inside_signUp")
        createUserInFirebase(email=registrationUIState.value.email,password=registrationUIState.value.password)
    }
    private fun createUserInFirebase(email:String,password:String){
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")


            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }

    private fun printState(){
        Log.d(TAG,"inside_printstate")
        Log.d(TAG,registrationUIState.value.toString())
    }
}