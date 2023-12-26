package com.yusuforhan.booksapp.android.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.components.CustomTextField

@Composable
fun LoginRoute(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    LoginScreen(
        navigateToSignup = navigateToSignup,
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Login") }) }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Column {
                CustomTextField(
                    value = email,
                    onValuesChange = { v -> email = v },
                    hint = stringResource(R.string.type_email),
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    value = password,
                    onValuesChange = { v -> password = v},
                    hint = stringResource(R.string.type_password),
                    singleLine = true
                )

                Button(onClick = {

                }) {
                    Text(text = "Click")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToSignup = { /*TODO*/ }) {

    }
}