package com.yusuforhan.booksapp.android.presentation.pay

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties

@Composable
fun PayRoute() {
    PayScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreen(
    modifier: Modifier = Modifier
) {
    val months = mutableListOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
    val dropDownOptions = mutableStateOf(listOf<String>())
    val textFieldValue = mutableStateOf(TextFieldValue())
    val dropDownExpanded = mutableStateOf(false)
    fun onDropdownDismissRequest() {
        dropDownExpanded.value = false
    }

    fun onValueChanged(value: TextFieldValue) {
        dropDownExpanded.value = true
        textFieldValue.value = value
    }


    var name by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                ),
                title = { Text(text = "Add Payment Methods", fontSize = 14.sp) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .size(20.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = name,
                onValueChange = { v -> name = v },
                placeholder = {
                    Text(
                        text = "Name on Card",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                },
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = cardNumber,
                onValueChange = { v -> cardNumber = v },
                placeholder = { Text(text = "Card Number", color = Color.Black, fontSize = 16.sp) },
            )
            TextFieldWithDropdown(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue.value,
                setValue = ::onValueChanged,
                onDismissRequest = ::onDropdownDismissRequest,
                dropDownExpanded = dropDownExpanded.value,
                list = dropDownOptions.value,
                label = "Month"
            )
        }
    }
}

@Preview
@Composable
fun PayScreenPreview() {
    PayScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropdown(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    dropDownExpanded: Boolean,
    list: List<String>,
    label: String = ""
) {
    Box(modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        onDismissRequest()
                },
            value = value,
            onValueChange = setValue,
            label = { Text(label) },
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
        DropdownMenu(
            expanded = dropDownExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissRequest
        ) {
            list.forEach { text ->
                DropdownMenuItem(
                    text = { Text(text = text) },
                    onClick = {
                        setValue(
                            TextFieldValue(text, TextRange(text.length))
                        )
                    }
                )
            }
        }
    }
}
