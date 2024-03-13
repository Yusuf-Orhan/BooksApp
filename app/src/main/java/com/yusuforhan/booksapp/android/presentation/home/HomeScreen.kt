package com.yusuforhan.booksapp.android.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
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
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.presentation.components.ECErrorScreen
import com.yusuforhan.booksapp.android.presentation.components.ECProgressBar
import com.yusuforhan.booksapp.android.presentation.components.ECSearchBar
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiEvent
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeUiState
import com.yusuforhan.booksapp.android.presentation.home.viewmodel.HomeViewModel
import com.yusuforhan.booksapp.android.presentation.theme.Light
import com.yusuforhan.booksapp.android.presentation.theme.Secondary

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(state = state, viewModel::handleEvent) {
        navigateToDetail(it)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    handleEvent: (HomeUiEvent) -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }
    Scaffold(
        containerColor = Light
    ) {
        Column(
            modifier = Modifier
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            ECSearchBar(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                value = searchQuery,
                onValueChanged = { s -> searchQuery = s },
                onSearch = { handleEvent(HomeUiEvent.SearchBooks(searchQuery)) }
            )
            if (state.categoryList.isNotEmpty()) {
                LazyRow {
                    item {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .background(
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                        ) {
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        selectedCategory = ""
                                        handleEvent(HomeUiEvent.GetBooks)
                                    }
                                    .padding(4.dp),
                                text = "All",
                                color = if (selectedCategory.isEmpty()) Red else White
                            )
                        }
                    }
                    items(state.categoryList.size) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .background(
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(6.dp)
                                ),
                        ) {
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        selectedCategory = state.categoryList[it].name
                                        handleEvent(HomeUiEvent.GetBooksByCategory(selectedCategory))
                                    }
                                    .padding(4.dp),
                                text = state.categoryList[it].name,
                                color = if (selectedCategory == state.categoryList[it].name) Red else White
                            )
                        }
                    }

                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(state.books) { book ->
                    ProductItem(product = book, onProductClick = navigateToDetail)
                }
            }
            if (state.loading) {
                ECProgressBar()
            } else if (state.error.isNotBlank()) {
                ECErrorScreen(
                    desc = state.error,
                    buttonText = stringResource(id = R.string.try_again),
                    onButtonClick = { handleEvent(HomeUiEvent.GetBooks) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Book,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            onProductClick(product.id)
        },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White
        ),
        border = BorderStroke(width = 1.dp, color = Secondary)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .height(100.dp)
                    .clipToBounds(),
                model = product.imageOne,
                contentDescription = "image",
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .height(40.dp),
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
                    color = Red
                )
            }
        }
    }
}