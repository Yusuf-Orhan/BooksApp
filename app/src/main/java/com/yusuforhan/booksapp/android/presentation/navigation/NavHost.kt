package com.yusuforhan.booksapp.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yusuforhan.booksapp.android.presentation.auth.login.LoginRoute
import com.yusuforhan.booksapp.android.presentation.auth.login.loginScreen
import com.yusuforhan.booksapp.android.presentation.auth.signup.SignupRoute
import com.yusuforhan.booksapp.android.presentation.auth.signup.navigateToSignup
import com.yusuforhan.booksapp.android.presentation.auth.signup.signupScreen
import com.yusuforhan.booksapp.android.presentation.home.homeScreen
import com.yusuforhan.booksapp.android.presentation.home.navigateToHome
import com.yusuforhan.booksapp.android.presentation.profile.profileScreen

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
            startDestination = Screen.LoginScreen.route
        ) {
            composable(route = Screen.LoginScreen.route) {
                LoginRoute(
                    navigateToSignup = navHostController::navigateToSignup,
                    navigateToHome = navHostController::navigateToHome
                )
            }
            composable(route = Screen.SignUpScreen.route) {
                SignupRoute(
                    navigateToHome = navHostController::navigateToHome
                )
            }
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