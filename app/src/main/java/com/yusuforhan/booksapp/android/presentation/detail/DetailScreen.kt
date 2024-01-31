package com.yusuforhan.booksapp.android.presentation.detail

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailState
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    viewModel : DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailScreen(state)
}

@Composable
fun DetailScreen(
    state : DetailState
) {
    if (state.isLoading == true) {
        CircularProgressIndicator()
    }
    else if (state.isError != null) {
        Text(text = state.isError)
    } else {
        AsyncImage(model = state.book?.imageOne, contentDescription = null)
        Text(text = state.book?.title ?: "Null")
    }

}