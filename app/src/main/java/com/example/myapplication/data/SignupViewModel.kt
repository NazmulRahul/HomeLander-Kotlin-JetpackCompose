package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.firestore

class SignupViewModel: ViewModel() {
    private val TAG = SignupViewModel::class.simpleName
    var registrationUIState= mutableStateOf(RegistrationUIState())
    var loginUIState = mutableStateOf(LoginUIState())
    fun onEvent(event:SignupUIEvent){
        when(event){
            is SignupUIEvent.FirstNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    firstName=event.firstName
                )
                printState()
            }
            is SignupUIEvent.LastNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is SignupUIEvent.EmailChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignupUIEvent.LocationChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    location = event.location
                )
                printState()
            }
            is SignupUIEvent.PasswordChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignupUIEvent.RegisterButtonClicked->{
                val db = Firebase.firestore
                val newProfile = hashMapOf(
                    "Fname" to registrationUIState.value.firstName,
                    "Lname" to registrationUIState.value.lastName,
                    "Location" to registrationUIState.value.location,
                    "Email" to registrationUIState.value.email
                )
                db.collection("profile")
                    .add(newProfile)
                    .addOnSuccessListener { documentReference ->
                        Log.d("newHouseAdded", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("failedHouseAdded", "Error adding document", e)
                    }
                signUp()
                printState()
            }
            is SignupUIEvent.LogoutButtonClicked->{
                logout()
                loginUIState.value=loginUIState.value.copy(
                    email=""
                )
                loginUIState.value=loginUIState.value.copy(
                    password=""
                )
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
                if(it.isSuccessful){
                    AppRouter.navigateTo(Screen.LogInScreen)
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }
    fun logout(){
        val firebaseAuth=FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener=AuthStateListener{
            if(it.currentUser==null){

            }
        }

        AppRouter.navigateTo(Screen.LogInScreen)
    }

    private fun printState(){
        Log.d(TAG,"inside_printstate")
        Log.d(TAG,registrationUIState.value.toString())
    }
}