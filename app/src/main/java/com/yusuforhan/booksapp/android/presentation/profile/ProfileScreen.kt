package com.yusuforhan.booksapp.android.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

@Composable
fun ProfileRoute() {
    ProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Profile") }) }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row (
                    modifier = modifier.fillMaxWidth().padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        //modifier = Modifier.padding(5.dp),
                        text = "Username : Yusuf Orhan"
                    )
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Email : orhanyusuf2534@gmail.com"
                )
                val password = "yusuf123"
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Password : ${password.passWordType()}",
                )
            }
        }
    }
}





fun String.passWordType()  : String{
    val text = this.substring(0, 2)
    val hidden = "*".repeat(this.length - 2)
    return "$text${hidden}"
}
