package com.yusuforhan.booksapp.android.presentation.pay

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen

fun NavGraphBuilder.paymentScreen(
    navigateUp : () -> Unit,
    navigateToSuccessScreen : (Int) -> Unit = {}
) {
    composable(
        route = Screen.PaymentScreen.route
    ){
        PayRoute(navigateUp)
    }
}

fun NavHostController.navigateToPayScreen() {
    navigate(
        route = Screen.PaymentScreen.route,
    )
}

