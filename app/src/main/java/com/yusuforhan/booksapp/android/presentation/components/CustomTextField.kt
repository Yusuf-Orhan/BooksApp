package com.yusuforhan.booksapp.android.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValuesChange: (String) -> Unit,
    hint: String,
    singleLine: Boolean,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValuesChange(it) },
        isError = isError,
        singleLine = singleLine,
        shape = RoundedCornerShape(16.dp),
        placeholder = { Text(text = hint) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorResource(id = R.color.neutral_100),
            errorBorderColor = Color.Red,
            focusedBorderColor = colorResource(id = R.color.primary_400)
        )
    )
}