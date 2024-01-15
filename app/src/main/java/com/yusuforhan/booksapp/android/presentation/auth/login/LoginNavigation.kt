package com.yusuforhan.booksapp.android.presentation.auth.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlin.math.log

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(
    navigateToSignup : () -> Unit,
    navigateToHome : () -> Unit
){
    composable(route = loginRoute) {
        LoginRoute(
            navigateToSignup = navigateToSignup,
            navigateToHome = navigateToHome
        )
    }
}

fun NavHostController.navigateToLogin(
    navOptions: NavOptions? = null
) {
    navigate(
        route = loginRoute,
        navOptions = navOptions
    )
}
