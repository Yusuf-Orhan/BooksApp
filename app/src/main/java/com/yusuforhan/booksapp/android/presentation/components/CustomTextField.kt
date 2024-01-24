package com.yusuforhan.booksapp.android.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.theme.BorderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValuesChange: (String) -> Unit,
    hint: String,
    titleText : String = "",
    singleLine: Boolean= false,
    isError: Boolean = false,
) {
    Column {
        if (titleText.isNotEmpty()) {
            Text(text = titleText,modifier = modifier.padding(start = 5.dp, bottom = 10.dp), color = BorderColor)
        }
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = { onValuesChange(it) },
            isError = isError,
            singleLine = singleLine,
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = hint) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                errorBorderColor = Color.Red,
                focusedBorderColor = BorderColor,
                unfocusedBorderColor = BorderColor
            )
        )
    }

}