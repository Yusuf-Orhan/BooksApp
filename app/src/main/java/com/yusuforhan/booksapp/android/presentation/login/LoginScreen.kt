package com.yusuforhan.booksapp.android.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.components.CustomTextField
import com.yusuforhan.booksapp.android.presentation.components.PasswordOutlinedTextField
import com.yusuforhan.booksapp.android.presentation.components.PasswordState
import com.yusuforhan.booksapp.android.presentation.components.rememberPasswordState

@Composable
fun LoginRoute(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {
    LoginScreen(
        navigateToSignup = navigateToSignup,
        navigateToHome = navigateToHome,
        LocalContext.current
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
    context : Context
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Login") }) }
    ) {
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            var passwordState = remember { PasswordState() }
            var password by remember { mutableStateOf("") }

            PasswordOutlinedTextField(state = passwordState)


            Button(onClick = {
                Toast.makeText(context,passwordState.password,Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Login")
            }
        }
    }
}
