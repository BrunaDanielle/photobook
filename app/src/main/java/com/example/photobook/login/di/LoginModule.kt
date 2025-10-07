package com.example.photobook.login.di

import com.example.photobook.login.domain.usecase.ValidateLoginDefaultUseCase
import com.example.photobook.login.domain.usecase.ValidateLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideValidateLoginUseCase(): ValidateLoginUseCase {
        return ValidateLoginDefaultUseCase()
    }
}