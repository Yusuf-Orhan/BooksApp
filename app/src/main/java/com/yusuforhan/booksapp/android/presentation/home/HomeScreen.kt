package com.yusuforhan.booksapp.android.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.data.model.remote.Books
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiState
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(state = state) {
        navigateToDetail(it)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    navigateToDetail: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.home)) }) }
    ) {
        Column(
            modifier = Modifier.padding(it),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(state.books) { book ->
                    ProductItem(product = book, onProductClick = navigateToDetail)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksItem(
    modifier: Modifier = Modifier,
    book: Books,
    navigateToDetail : (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {navigateToDetail(book.id)}
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
            modifier = modifier
                .padding(5.dp)
                .height(30.dp),
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
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Books,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onProductClick(product.id)
            }
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .height(100.dp)
                    .clipToBounds(),
                model = product.imageOne,
                contentDescription = "image",
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp).height(40.dp),
                text = product.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = product.price.toString(),
                textDecoration = TextDecoration.LineThrough
            )

            if (product.saleState) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 8.dp),
                    text = product.salePrice.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }
        }
    }
}