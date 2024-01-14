package com.yusuforhan.booksapp.android.presentation.auth.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val signupRoute = "signup_route"

fun NavGraphBuilder.signupScreen(
    navigateToHome: () -> Unit
) {
    composable(route = signupRoute) {
        SignupRoute(
            navigateToHome = navigateToHome
        )
    }
}

fun NavHostController.navigateToSignup(
    navOptions: NavOptions? = null
) {
    navigate(
        route = signupRoute,
        navOptions = navOptions
    )
}