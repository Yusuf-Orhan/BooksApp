package com.yusuforhan.booksapp.android.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.data.model.remote.Books
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
    state: HomeUiState
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Anasayfa") }) }
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(state.books) { book ->
                    BooksItem(book = book)
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


@Composable
fun BooksItem(
    modifier: Modifier = Modifier,
    book: Books
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = book.imageOne,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .height(100.dp)
                .clipToBounds()
        )
        Text(
            modifier = modifier.padding(5.dp).height(30.dp),
            text = book.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,

        )
        Text(
            modifier = modifier.padding(5.dp),
            text = "$${book.price}",
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Red
        )
    }
}