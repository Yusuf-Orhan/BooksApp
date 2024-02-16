package com.yusuforhan.booksapp.android.presentation.pay

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.homeRoute
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.loginRoute


fun NavGraphBuilder.paymentScreen(
    navigateUp : () -> Unit,
    navigateToSuccessScreen : (Int) -> Unit = {}
) {
    composable(
        route = homeRoute
    ){
        PayScreen()
    }
}

fun NavHostController.navigateToPayScreen(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Screen.PaymentScreen.route,
    )
}

