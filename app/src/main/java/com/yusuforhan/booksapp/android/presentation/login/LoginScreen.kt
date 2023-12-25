package com.yusuforhan.booksapp.android.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer

@Composable
fun LoginRoute(
    navigateToSignup : () -> Unit,
    navigateToHome : () -> Unit
) {
    LoginScreen(
        navigateToSignup = navigateToSignup,
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToSignup : () -> Unit,
    navigateToHome : () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Login")}) }
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                ElevatedButton(onClick = { navigateToHome() }) {
                    Text(text = "Navigate To Home")
                }
                ElevatedButton(onClick = { navigateToSignup() }) {
                    Text(text = "Navigate To SignUp")
                }
            }

        }
    }
}