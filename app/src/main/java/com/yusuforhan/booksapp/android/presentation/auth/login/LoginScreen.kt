package com.yusuforhan.booksapp.android.presentation.auth.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.common.DataStoreHelper
import com.yusuforhan.booksapp.android.common.showToast
import com.yusuforhan.booksapp.android.data.model.remote.SignInModel
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthEvent
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthState
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthViewModel
import com.yusuforhan.booksapp.android.presentation.components.BAButton
import com.yusuforhan.booksapp.android.presentation.components.CustomTextField
import com.yusuforhan.booksapp.android.presentation.components.PasswordOutlinedTextField
import com.yusuforhan.booksapp.android.presentation.components.rememberPasswordState
import com.yusuforhan.booksapp.android.presentation.theme.Blue100
import com.yusuforhan.booksapp.android.presentation.theme.Primary
import com.yusuforhan.booksapp.android.presentation.theme.Purple_1
import com.yusuforhan.booksapp.android.presentation.theme.Secondary

@Composable
fun LoginRoute(
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        navigateToSignup = navigateToSignup,
        navigateToHome = navigateToHome,
        state = state,
        context = LocalContext.current,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
    state : AuthState,
    context: Context,
    onEvent : (AuthEvent) -> Unit,
) {
    if (state.emptyParameter == true) {
        showToast(context, stringResource(id = R.string.please_fill_in_the_blanks))
    } else if (state.isSuccess == true) {
        navigateToHome()
        showToast(context, stringResource(R.string.logged_in_successfully))
    } else if (state.isSuccess == false){
        showToast(context, state.message.orEmpty())
    }
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

            Image(painter = painterResource(id = R.drawable.ic_books), contentDescription = null)
            Spacer(modifier = modifier.size(20.dp))

            CustomTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = email,
                onValuesChange = { v -> email = v },
                hint = stringResource(R.string.enter_your_email),
                titleText = stringResource(R.string.email),
                singleLine = true
            )
            Spacer(modifier = modifier.size(20.dp))
            PasswordOutlinedTextField(
                state = password,
                hint = stringResource(R.string.enter_your_password),
                titleText = stringResource(R.string.password)
            )
            Spacer(modifier = modifier.size(24.dp))
            BAButton(
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp),
                onClick = {
                    val signInModel = SignInModel(email,password.password)
                    onEvent(AuthEvent.SignIn(signInModel = signInModel))
                },
                backgroundColor = Primary,
                text = stringResource(id = R.string.login)
            )
            Spacer(modifier = modifier.size(34.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(R.string.don_t_have_an_account))
                Text(
                    text = stringResource(R.string.sign_up),
                    color = Purple_1,
                    modifier = modifier
                        .padding(horizontal = 5.dp)
                        .clickable {
                            navigateToSignup()
                        }
                )
            }
        }
    }
}

