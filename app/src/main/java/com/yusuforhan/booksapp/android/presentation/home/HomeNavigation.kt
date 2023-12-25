package com.yusuforhan.booksapp.android.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val homeRoute = "homeRoute"

fun NavGraphBuilder.homeScreen() {
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
        navOptions = navOptions
    )
}

