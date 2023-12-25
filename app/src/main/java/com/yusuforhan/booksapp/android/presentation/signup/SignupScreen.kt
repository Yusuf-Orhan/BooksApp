package com.yusuforhan.booksapp.android.presentation.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignupRoute(
    navigateToHome : () -> Unit
) {
    SignupScreen(navigateToHome = navigateToHome)
}

@Composable
fun SignupScreen(
    navigateToHome : () -> Unit
) {
    Text(text = "Signup Screen")
}