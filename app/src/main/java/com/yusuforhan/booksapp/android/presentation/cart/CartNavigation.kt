package com.yusuforhan.booksapp.android.presentation.cart

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen

fun NavGraphBuilder.cartScreen(
    navigateUp: () -> Unit
) {
    composable(
        route = Screen.cartRoute
    ) {
        CartRoute()
    }
}

fun NavHostController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Screen.cartRoute
    )
}
