package com.example.photobook.presentation.composes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.photobook.chat.presentation.composes.ChatScreen
import com.example.photobook.home.presentation.composes.HomeScreen
import com.example.photobook.login.presentation.composes.screen.LoginScreen
import com.example.photobook.login.presentation.viewmodel.LoginViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("home") {
            HomeScreen(
                onNavigateToChat = {
                    navController.navigate("chat")
                }
            )
        }

        composable("chat") {
            ChatScreen()
        }
    }
}
