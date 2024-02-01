package com.yusuforhan.booksapp.android.presentation.detail

import android.graphics.fonts.FontStyle
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailState
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailScreen(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Book Detail") }) }
    ) {
        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading == true) {
                CircularProgressIndicator()
            } else if (state.isError != null) {
                Text(text = state.isError)
            } else {
                state.book?.let { book -> BookDetailContent(book = book) }
            }
        }
    }
}

@Composable
fun BookDetailContent(
    book: Books
) {
    AsyncImage(model = book.imageOne, contentDescription = null)
    Text(text = book.title)
    RatingView(rate = book.rate)
    Text(text = book.description)
}

@Composable
fun RatingView(
    rate: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.Red)
    ) {
        Icon(modifier= modifier.padding(5.dp),imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
        Text(modifier= modifier.padding(5.dp),text = rate.toString())
    }
}