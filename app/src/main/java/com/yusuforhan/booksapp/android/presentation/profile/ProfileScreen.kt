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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusuforhan.booksapp.android.presentation.components.ECProgressBar
import com.yusuforhan.booksapp.android.presentation.profile.viewmodel.ProfileState
import com.yusuforhan.booksapp.android.presentation.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
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
                Card(
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
                            text = "Username : ${state.userModel?.name}"
                        )
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Email : ${state.userModel?.email}"
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Phone : ${state.userModel?.phone}",
                    )
                }
            }
        }
    }
}


fun String.passWordType(): String {
    val text = this.substring(0, 2)
    val hidden = "*".repeat(this.length - 2)
    return "$text${hidden}"
}
