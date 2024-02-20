package com.yusuforhan.booksapp.android.presentation.pay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PayRoute() {
    PaymentScreen()
}
/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreen() {
    var cardholderName by mutableStateOf("")
    var cardNumber by mutableStateOf("")
    var expiryMonth by mutableStateOf("")
    var expiryYear by mutableStateOf("")
    var cvcCode by mutableStateOf("")
    var address by mutableStateOf("")

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = { Text("Card Number") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                OutlinedTextField(
                    value = expiryMonth,
                    onValueChange = { expiryMonth = it },
                    label = { Text("Month") },
                    modifier = Modifier.width(120.dp).padding(4.dp)
                )

                OutlinedTextField(
                    value = expiryYear,
                    onValueChange = { expiryYear = it },
                    label = { Text("Year") },
                    modifier = Modifier.width(120.dp).padding(4.dp)
                )
            }

            OutlinedTextField(
                value = cvcCode,
                onValueChange = { cvcCode = it },
                label = { Text("CVC") },
                modifier = Modifier.width(120.dp).padding(8.dp)
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Pay")
            }
        }
    }
}

 */
@Composable
fun PaymentScreen() {
    val cardHolderName = "Yusuf Orhan"
    val cardNumber = "5555555555555555"
    val expirationMonth = 1
    val expirationYear = 2023
    val cvc = "111"
    val address = "Test Adress"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        // Başlık
        Text(
            text = "Ödeme",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Kart Sahibi Bilgileri
        CardHolderInfo(
            cardHolderName = cardHolderName,
            cardNumber = cardNumber,
            expirationMonth = expirationMonth,
            expirationYear = expirationYear,
            cvc = cvc
        )

        // Adres Bilgileri
        AddressInfo(
            address = address
        )

        // Ödeme Butonu
        Button(
            onClick = { /* Ödeme işlemini gerçekleştir */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Ödeme Yap")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHolderInfo(
    cardHolderName: String,
    cardNumber: String,
    expirationMonth: Int,
    expirationYear: Int,
    cvc: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Kart Sahibi İsmi
        TextField(
            value = cardHolderName,
            onValueChange = {},
            label = { Text(text = "Kart Sahibi İsmi") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Kart Numarası
        TextField(
            value = cardNumber,
            onValueChange = {},
            label = { Text(text = "Kart Numarası") },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Son Kullanma Tarihi
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Ay
            TextField(
                value = expirationMonth.toString(),
                onValueChange = {},
                label = { Text(text = "Ay") },
                modifier = Modifier.width(IntrinsicSize.Max)
            )

            // Yıl
            TextField(
                value = expirationYear.toString(),
                onValueChange = {},
                label = { Text(text = "Yıl") },
                modifier = Modifier.width(IntrinsicSize.Max)
            )
        }

        // CVC
        TextField(
            value = cvc,
            onValueChange = {},
            label = { Text(text = "CVC") },
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressInfo(
    address: String
) {
    // Adres
    TextField(
        value = address,
        onValueChange = {},
        label = { Text(text = "Adres") },
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
