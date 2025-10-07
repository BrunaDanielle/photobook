package com.example.photobook.login.presentation.viewmodel

sealed class LoginUiEvent {
    object NavigateToHome : LoginUiEvent()
    data class ShowError(val message: String) : LoginUiEvent()
}