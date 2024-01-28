package com.yusuforhan.booksapp.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yusuforhan.booksapp.android.presentation.auth.login.LoginRoute
import com.yusuforhan.booksapp.android.presentation.auth.login.loginScreen
import com.yusuforhan.booksapp.android.presentation.auth.signup.navigateToSignup
import com.yusuforhan.booksapp.android.presentation.auth.signup.signupScreen
import com.yusuforhan.booksapp.android.presentation.home.homeScreen
import com.yusuforhan.booksapp.android.presentation.home.navigateToHome

@Composable
fun BooksNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        navigation(
            route = Screen.LoginNavigation.route,
            startDestination = Screen.loginRoute
        ) {
            loginScreen(
                navigateToSignup = { navHostController.navigateToSignup() },
                navigateToHome = { navHostController.navigateToHome() }
            )
            signupScreen(
                navigateToHome = {
                    navHostController.navigateToHome()
                }
            )
        }
        navigation(
            route = Screen.BooksNavigation.route,
            startDestination = Screen.NavigatorScreen.route
        ) {
            composable(route = Screen.NavigatorScreen.route) {
                BooksNavigator()
            }
        }
    }
}
