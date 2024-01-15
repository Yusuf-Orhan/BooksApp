package com.yusuforhan.booksapp.android.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.auth.login.loginRoute

const val homeRoute = "home_route"

fun NavGraphBuilder.homeScreen(
    navigateUp : () -> Unit
) {
    composable(
        route = homeRoute
    ){
        HomeRoute()
    }
}

fun NavHostController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(
        route = homeRoute,
    ) {
        popUpTo(loginRoute) { inclusive = true }
    }
}

