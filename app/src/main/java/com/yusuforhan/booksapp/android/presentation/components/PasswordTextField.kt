package com.yusuforhan.booksapp.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.theme.BorderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordOutlinedTextField(state: PasswordState,hint : String,titleText : String="",modifier : Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        if (titleText.isNotEmpty()) {
            Text(text = titleText,modifier = modifier.padding(start = 5.dp, bottom = 10.dp), color = BorderColor)
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = {
                state.password = it
            },
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = hint) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                errorBorderColor = Color.Red,
                focusedBorderColor = BorderColor,
                unfocusedBorderColor = BorderColor
            ),
            trailingIcon = {
                if (state.passwordVisibility) {
                    Icon(
                        modifier = Modifier.clickable {
                            state.passwordVisibility = false
                        },
                        painter = painterResource(id = R.drawable.ic_visibility_on),
                        contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier.clickable {
                            state.passwordVisibility = true
                        },
                        painter = painterResource(id = R.drawable.ic_visibility_off),
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (state.passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            singleLine = true
        )
    }

}

@Composable
fun rememberPasswordState() = remember { PasswordState() }

class PasswordState {
    var password by mutableStateOf("")
    var passwordVisibility by mutableStateOf(false)
}


