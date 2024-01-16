package com.yusuforhan.booksapp.android.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.booksapp.android.data.model.remote.BooksModel
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(viewModel = viewModel, booksList = viewModel.booksList.value)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    booksList : List<BooksModel>
) {
    Scaffold (
        topBar = { TopAppBar(title = { Text(text = "Anasayfa") }) }
    ){
        Column (
            modifier = Modifier.padding(it)
        ){

            LazyColumn {
                items(viewModel.booksList.value.size) {p ->
                    Text(text = viewModel.booksList.value[p].title)
                }
            }
        }
    }
}