package com.yusuforhan.booksapp.android.presentation.pay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardForm() {
    var cardholderName by mutableStateOf("")
    var cardNumber by mutableStateOf("")
    var expiryMonth by mutableStateOf("")
    var expiryYear by mutableStateOf("")
    var cvcCode by mutableStateOf("")
    var address by mutableStateOf("")

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "Payment",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = cardholderName,
                onValueChange = { cardholderName = it },
                label = { Text("Card Holder Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = { Text("Card Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = expiryMonth,
                    onValueChange = { expiryMonth = it },
                    label = { Text("Month") },
                    modifier = Modifier.width(120.dp)
                )

                OutlinedTextField(
                    value = expiryYear,
                    onValueChange = { expiryYear = it },
                    label = { Text("Year") },
                    modifier = Modifier.width(120.dp)
                )
            }

            OutlinedTextField(
                value = cvcCode,
                onValueChange = { cvcCode = it },
                label = { Text("CVC") },
                modifier = Modifier.width(120.dp)
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pay")
            }
        }
    }
}