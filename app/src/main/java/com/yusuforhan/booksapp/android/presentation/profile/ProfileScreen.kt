package com.yusuforhan.booksapp.android.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.booksapp.android.data.model.remote.User
import com.yusuforhan.booksapp.android.presentation.components.ECProgressBar
import com.yusuforhan.booksapp.android.presentation.profile.viewmodel.ProfileState
import com.yusuforhan.booksapp.android.presentation.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ProfileScreen(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileState,
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
            if (state.isLoading) {
                ECProgressBar()
            } else if (state.error.isNotBlank()) {
                Text(text = state.error)
            } else {
                /*Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Username : ${state.userModel?.user?.name}"
                        )
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Email : ${state.userModel?.user?.email}"
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Phone : ${state.userModel?.user?.phone}",
                    )
                }

                 */
                ProfileContent(userModel = state.userModel?.user!!)
            }
        }
    }
}

@Composable
fun ProfileContent(
    userModel: User
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "account image",
            modifier = Modifier.size(150.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Email",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                )
            )
            Text(
                text = userModel.email,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Nickname",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                )
            )
            Text(
                text = userModel.name,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Phone Number",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp
                )
            )
            Text(
                text = userModel.phone,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sign Out")

        }
    }
}