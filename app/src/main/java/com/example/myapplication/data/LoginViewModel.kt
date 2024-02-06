package com.example.myapplication.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var registrationUIState= mutableStateOf(RegistrationUIState())
    fun onEvent(event:UIEvent){
        when(event){
            is UIEvent.FirstNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    firstName=event.firstName
                )
            }
            is UIEvent.LastNameChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is UIEvent.EmailChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    lastName = event.email
                )
            }
            is UIEvent.PasswordChanged->{
                registrationUIState.value=registrationUIState.value.copy(
                    lastName = event.password
                )
            }
            is UIEvent.RegisterButtonClicked->{
                signUp()
            }
        }
    }
    private fun signUp(){}

}