package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.navigation.AppRouter
import com.example.myapplication.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
class LoginViewModel: ViewModel() {
    private val TAG = SignupViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginUIState())
    fun onEvent(event:LoginUIEvent){
        when(event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value=loginUIState.value.copy(
                    email=event.email
                )

            }
            is LoginUIEvent.PasswordChanged->{
                loginUIState.value=loginUIState.value.copy(
                    password=event.password
                )
            }
            is LoginUIEvent.LoginButtonClicked->{
                login()
            }
            is LoginUIEvent.LogoutButtonClicked->{
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
    fun logout(){
        val firebaseAuth=FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener= FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {

            }
        }

        AppRouter.navigateTo(Screen.LogInScreen)
    }
    private fun login(){
        val email=loginUIState.value.email
        val password=loginUIState.value.password
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                Log.d(TAG,"Inside_login_Success")
                Log.d(TAG,"&{it.isSuccessful}")
                if(it.isSuccessful){
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{

            }
    }

}