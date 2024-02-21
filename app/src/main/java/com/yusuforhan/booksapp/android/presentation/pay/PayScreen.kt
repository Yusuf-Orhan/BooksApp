package com.yusuforhan.booksapp.android.presentation.pay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PayRoute() {
    PayScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreen(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                ),
                title = { Text(text = "Payment", fontSize = 16.sp) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = 18.dp)
                            .size(24.dp),
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
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = month,
                    onValueChange = { v -> month = v },
                    placeholder = { Text(text = "Month", color = Color.Black, fontSize = 16.sp) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = year,
                    onValueChange = { v -> year = v },
                    placeholder = { Text(text = "Year", color = Color.Black, fontSize = 16.sp) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    )
                )
                OutlinedTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = cvc,
                    onValueChange = { v -> cvc = v },
                    placeholder = { Text(text = "CVC", color = Color.Black, fontSize = 16.sp) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    )
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .weight(5f),
                value = address,
                onValueChange = { v -> address = v },
                placeholder = { Text(text = "Address", color = Color.Black, fontSize = 16.sp) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = {

                }
            ) {
                Text(text = "Pay")
            }
        }
    }
}

