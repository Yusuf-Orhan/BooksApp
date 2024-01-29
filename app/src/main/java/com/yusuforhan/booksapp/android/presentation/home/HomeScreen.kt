package com.yusuforhan.booksapp.android.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiState
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(state = state)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state : HomeUiState
) {
    Scaffold (
        topBar = { TopAppBar(title = { Text(text = "Anasayfa") }) }
    ){
        Column (
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LazyColumn {
                items(state.books) {books ->
                    Text(text = books.title)
                }
            }
            if (state.loading) {
                CircularProgressIndicator()
            } else if (state.error.isNotBlank()) {
                Text(text = state.error)
            }
        }
    }
}