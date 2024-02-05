package com.yusuforhan.booksapp.android.presentation.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.booksapp.android.presentation.cart.viewmodel.CartViewModel
import com.yusuforhan.booksapp.android.presentation.home.ProductItem
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiState

@Composable
fun CartRoute(
    viewModel: CartViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    CartScreen(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: HomeUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Cart") }) }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            if (state.loading) {
                CircularProgressIndicator()
            } else if (state.books.isNotEmpty()) {
                LazyColumn {
                    items(state.books) {
                         ProductItem(product = it, onProductClick = {})
                    }
                }
            } else if (state.error.isNotBlank()) {
                Text(text = state.error)
            }

        }
    }
}