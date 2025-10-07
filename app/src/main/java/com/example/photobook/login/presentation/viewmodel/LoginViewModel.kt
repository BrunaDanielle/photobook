package com.example.photobook.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photobook.login.domain.usecase.ValidateLoginUseCase
import com.example.photobook.login.presentation.model.LoginUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val useCase: ValidateLoginUseCase
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiModel(
        email = "",
        password = ""
    ))
        private set

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EmailChanged -> onEmailChanged(action.email)
            is LoginAction.PasswordChanged -> onPasswordChanged(action.password)
            is LoginAction.Submit -> {
                viewModelScope.launch {
                    val isValid = useCase.invoke(
                        email = uiState.email,
                        password = uiState.email
                    )

                    if (isValid) {
                        _uiEvent.emit(LoginUiEvent.NavigateToHome)
                    } else {
                        _uiEvent.emit(LoginUiEvent.ShowError("Credenciais inválidas"))
                    }
                }
            }
        }
    }

    fun onEmailChanged(newEmail: String) {
        uiState = uiState.copy(email = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        uiState = uiState.copy(password = newPassword)
    }
}
