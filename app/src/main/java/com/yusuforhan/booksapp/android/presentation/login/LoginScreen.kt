package com.yusuforhan.booksapp.android.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.components.PasswordOutlinedTextField
import com.yusuforhan.booksapp.android.presentation.components.PasswordState
import com.yusuforhan.booksapp.android.presentation.components.rememberPasswordState
import com.yusuforhan.booksapp.android.ui.theme.BooksAppTheme
import com.yusuforhan.booksapp.android.ui.theme.Purple40

@Composable
fun LoginRoute(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    LoginScreen(
        navigateToSignup = navigateToSignup,
        navigateToHome = navigateToHome,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
) {
    Scaffold(
        containerColor = Color.White
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val password = rememberPasswordState()
            var email by remember { mutableStateOf("") }

            PasswordOutlinedTextField(state = password)
            PasswordOutlinedTextField(state = password)
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Login")
            }
        }
    }
}
