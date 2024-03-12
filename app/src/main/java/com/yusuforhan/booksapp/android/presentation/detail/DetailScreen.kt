package com.yusuforhan.booksapp.android.presentation.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.data.model.remote.CartModel
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailEvent
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailState
import com.yusuforhan.booksapp.android.presentation.detail.viewmodel.DetailViewModel

@Composable
fun DetailRoute(
    navigateUp: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    DetailScreen(state, LocalContext.current, navigateUp, viewModel::handleEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    context: Context,
    navigateUp: () -> Unit,
    handleEvent: (DetailEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isFavorite by remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier.padding(8.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.book_detail)) },
                navigationIcon = {
                    Icon(
                        modifier = modifier.clickable {
                            navigateUp()
                        },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                },
                actions = {
                    Icon(
                        modifier = modifier.clickable {
                            state.book?.let {
                                handleEvent(
                                    DetailEvent.AddToFavorite(
                                        FavoriteEntity(it.id, it)
                                    )
                                )
                            }
                        },
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else Color.Black
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading == true) {
                CircularProgressIndicator()
            } else if (state.isError != null) {
                Text(text = state.isError)
            } else if (state.addToCart == true) {
                navigateUp()
                Toast.makeText(
                    context,
                    stringResource(R.string.book_added_cart),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (state.addToCart == false) {
                navigateUp()
                Toast.makeText(
                    context,
                    stringResource(R.string.detail_toast_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                state.book?.let { book ->
                    BookDetailContent(book = book, handleEvent, state.userId!!)
                    isFavorite = state.isFavorite ?: false
                }
            }
        }
    }
}

@Composable
fun BookDetailContent(
    book: Book,
    handleEvent: (DetailEvent) -> Unit,
    userId: String,
    modifier: Modifier = Modifier
) {
add
    AsyncImage(
        modifier = modifier.size(300.dp),
        model = book.imageOne,
        contentDescription = null
    )
    Spacer(modifier = modifier.size(8.dp))
    Text(text = book.title)
    Spacer(modifier = modifier.size(8.dp))
    RatingView(rate = book.rate)
    Spacer(modifier = modifier.size(8.dp))
    Text(text = book.description)
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = book.price.toString())
        Button(
            onClick = {
                handleEvent(DetailEvent.AddCart(CartModel(book.id, userId)))
            }
        ) {
            Text(text = stringResource(R.string.add_cart))
        }
    }
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
        Icon(
            modifier = modifier.padding(5.dp),
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.Yellow
        )
        Text(modifier = modifier.padding(8.dp), text = rate.toString())
    }
}