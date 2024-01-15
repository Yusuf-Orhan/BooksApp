package com.yusuforhan.booksapp.android.presentation.auth.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.common.showToast
import com.yusuforhan.booksapp.android.data.model.remote.SignUpModel
import com.yusuforhan.booksapp.android.presentation.components.CustomTextField
import com.yusuforhan.booksapp.android.presentation.components.PasswordOutlinedTextField
import com.yusuforhan.booksapp.android.presentation.components.rememberPasswordState
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthEvent
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthState
import com.yusuforhan.booksapp.android.presentation.auth.viewmodel.AuthViewModel
import com.yusuforhan.booksapp.android.ui.theme.Blue100

@Composable
fun SignupRoute(
    navigateToHome: () -> Unit, viewModel: AuthViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignupScreen(
        navigateToHome = navigateToHome,
        state = state,
        onEvent = viewModel::onEvent,
        context = LocalContext.current
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    onEvent: (AuthEvent) -> Unit,
    state: AuthState,
    context: Context
) {
    val password = rememberPasswordState()
    var email by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    if (state.isSuccess == true) {
        navigateToHome()
        showToast(context, stringResource(R.string.user_created_successfully))
    } else if (state.emptyParameter == true) {
        Toast.makeText(context, stringResource(R.string.please_fill_in_the_blanks), Toast.LENGTH_SHORT).show()
    } else if (state.isSuccess == false) {
        Toast.makeText(context, stringResource(R.string.an_unexpected_error), Toast.LENGTH_SHORT).show()
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
            Image(
                painter = painterResource(id = R.drawable.ic_books),
                contentDescription = null
            )
            Text(text = stringResource(R.string.create_an_account), fontSize = 24.sp)
            Spacer(modifier = modifier.size(20.dp))
            CustomTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = userName,
                onValuesChange = { v -> userName = v },
                hint = stringResource(R.string.enter_your_username),
                singleLine = true
            )
            Spacer(modifier = modifier.size(20.dp))
            CustomTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = email,
                onValuesChange = { v -> email = v },
                hint = stringResource(id = R.string.enter_your_email),
                singleLine = true
            )
            Spacer(modifier = modifier.size(20.dp))
            CustomTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = phoneNumber,
                onValuesChange = { v -> phoneNumber = v },
                hint = stringResource(R.string.enter_your_phone_number),
                singleLine = true
            )
            Spacer(modifier = modifier.size(20.dp))
            CustomTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = address,
                onValuesChange = { v -> address = v },
                hint = stringResource(R.string.enter_your_address),
                singleLine = true
            )
            Spacer(modifier = modifier.size(20.dp))

            PasswordOutlinedTextField(
                state = password, hint = stringResource(R.string.enter_your_password)
            )
            Spacer(modifier = modifier.size(24.dp))
            Button(modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue100
                ),
                onClick = {
                    val signUpModel = SignUpModel(
                        address = address,
                        email = email,
                        name = userName,
                        password = password.password,
                        phone = phoneNumber
                    )
                    onEvent(AuthEvent.Auth(signUpModel))
                }) {
                Text(
                    text = stringResource(R.string.sign_up),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}