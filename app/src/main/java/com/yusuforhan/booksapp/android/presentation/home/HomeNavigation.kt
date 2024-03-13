package com.yusuforhan.booksapp.android.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.homeRoute
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.loginRoute


fun NavGraphBuilder.homeScreen(
    navigateToDetail : (Int) -> Unit
) {
    composable(
        route = homeRoute
    ){
        HomeRoute(navigateToDetail = navigateToDetail)
    }
}

fun NavHostController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Screen.NavigatorScreen.route,
    ) {
        popUpTo(loginRoute) { inclusive = true }
    }
}

