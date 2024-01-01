package com.yusuforhan.booksapp.android.presentation.login

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = email,
                    onValuesChange = { v -> email = v },
                    hint = stringResource(R.string.type_email),
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = password,
                    onValuesChange = { v -> password = v },
                    hint = stringResource(R.string.type_password),
                    singleLine = true
                )

                Button(
                    modifier = Modifier.align(CenterHorizontally).animateClick(enabled = true).fillMaxWidth().padding(16.dp),
                    onClick = {

                    }
                ) {
                    Text(text = "Click")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToSignup = { /*TODO*/ }) {}
}


@Composable
fun Modifier.animateClick(enabled: Boolean = true): Modifier = composed {
    if (enabled) {
        var clickState by remember { mutableStateOf(ClickState.Idle) }
        val scale by animateFloatAsState(
            targetValue = if (clickState == ClickState.Pressed) 0.925f else 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            ),
            label = "bouncyClick"
        )

        graphicsLayer {
            scaleX = scale
            scaleY = scale
        }.pointerInput(clickState) {
            awaitPointerEventScope {
                clickState = if (clickState == ClickState.Pressed) {
                    waitForUpOrCancellation()
                    ClickState.Idle
                } else {
                    awaitFirstDown(false)
                    ClickState.Pressed
                }
            }
        }
    } else {
        this
    }
}

enum class ClickState {
    Pressed,
    Idle
}
