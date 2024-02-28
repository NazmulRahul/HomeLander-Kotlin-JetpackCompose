package com.example.myapplication

import com.example.myapplication.data.SignupUIEvent


sealed class HouseDataUiEvent {
    data class AddressChanged(val address:String):HouseDataUiEvent()
    data class DescriptionChanged(val description:String): HouseDataUiEvent()
    data class ImageChanged(val image:String): HouseDataUiEvent()
    data class RentChanged(val rent:String): HouseDataUiEvent()
    object UploadButtonClicked: HouseDataUiEvent()
}