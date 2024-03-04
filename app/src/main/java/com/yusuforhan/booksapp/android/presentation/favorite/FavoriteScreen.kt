package com.yusuforhan.booksapp.android.presentation.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.presentation.favorite.viewmodel.FavoriteState
import com.yusuforhan.booksapp.android.presentation.favorite.viewmodel.FavoriteUiEvent
import com.yusuforhan.booksapp.android.presentation.favorite.viewmodel.FavoriteViewModel

@Composable
fun FavoriteRoute(
    navigateToDetail: (Int) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getFavorite()
    }
    FavoriteScreen(
        state = state,
        navigateToDetail = navigateToDetail,
        onDeleteClick = { viewModel.handleEvent(FavoriteUiEvent.DeleteFromFavorite(it)) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onDeleteClick: (FavoriteEntity) -> Unit,
    navigateToDetail: (Int) -> Unit,
    state: FavoriteState
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.favorite)) }) }
    ) {
        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.booksEmpty == true) {
                Text(text = "No books have been added to favorites.")
            } else {
                LazyColumn {
                    items(state.favoriteBooks) { entity ->
                        FavoriteItem(
                            book = entity.book,
                            onDeleteClick = onDeleteClick,
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteItem(
    book: Book,
    onDeleteClick: (FavoriteEntity) -> Unit,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = { navigateToDetail(book.id) }
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
                            .width(100.dp),
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
                    modifier = modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = modifier.clickable {
                            onDeleteClick(
                                FavoriteEntity(
                                    book.id,
                                    book
                                )
                            )
                        },
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Column(
                    modifier = modifier.align(Alignment.BottomEnd)
                ) {
                    Text(
                        modifier = modifier.padding(start = 8.dp, end = 10.dp),
                        text = "$${book.price}",
                        color = Color.Red
                    )
                }
            }
        }
    }
}


