package com.example.photobook.login.domain.usecase

interface ValidateLoginUseCase {
    fun invoke(email: String, password: String): Boolean
}

class ValidateLoginDefaultUseCase: ValidateLoginUseCase {

    override operator fun invoke(email: String, password: String): Boolean {
        val isEmailValid = email.endsWith("@gmail.com", ignoreCase = true)
        val isPasswordValid = password.length >= 4
        return isEmailValid && isPasswordValid
    }
}
