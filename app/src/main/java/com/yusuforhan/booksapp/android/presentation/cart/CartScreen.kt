package com.yusuforhan.booksapp.android.presentation.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.presentation.cart.viewmodel.CartViewModel
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
                        CartItem(book = it)
                    }
                }
            } else if (state.error.isNotBlank()) {
                Text(text = state.error)
            }

        }
    }
}


@Composable
fun CartItem(
    book: Books,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row {
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = modifier
                            .align(alignment = Alignment.CenterVertically)
                            .fillMaxHeight()
                            .width(150.dp),
                        model = book.imageOne,
                        contentDescription = "image",
                    )

                    Text(
                        modifier = modifier.padding(8.dp),
                        text = book.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    modifier = modifier.align(Alignment.BottomEnd)
                ) {
                    Text(
                        modifier = modifier
                            .padding(start = 8.dp),
                        text = book.price.toString(),
                        textDecoration = TextDecoration.LineThrough
                    )
                    if (book.saleState) {
                        Text(
                            modifier = modifier
                                .padding(start = 8.dp, bottom = 8.dp),
                            text = book.salePrice.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}