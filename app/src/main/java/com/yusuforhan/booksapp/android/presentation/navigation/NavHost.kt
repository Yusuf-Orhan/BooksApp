package com.yusuforhan.booksapp.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yusuforhan.booksapp.android.presentation.home.homeScreen
import com.yusuforhan.booksapp.android.presentation.home.navigateToHome
import com.yusuforhan.booksapp.android.presentation.auth.login.loginScreen
import com.yusuforhan.booksapp.android.presentation.auth.signup.navigateToSignup
import com.yusuforhan.booksapp.android.presentation.auth.signup.signupScreen

@Composable
fun BooksNavHost(
    navHostController: NavHostController,
    startDestination : String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        loginScreen(
            navigateToSignup = { navHostController.navigateToSignup() },
            navigateToHome = { navHostController.navigateToHome() }
        )
        signupScreen(
            navigateToHome = {navHostController.navigateToHome()}
        )
        homeScreen()
    }

}