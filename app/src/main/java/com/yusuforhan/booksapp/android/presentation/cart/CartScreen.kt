package com.yusuforhan.booksapp.android.presentation.cart

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.common.calculateTotalPrice
import com.yusuforhan.booksapp.android.data.model.remote.Book
import com.yusuforhan.booksapp.android.presentation.cart.viewmodel.CartUiEvent
import com.yusuforhan.booksapp.android.presentation.cart.viewmodel.CartUiState
import com.yusuforhan.booksapp.android.presentation.cart.viewmodel.CartViewModel
import com.yusuforhan.booksapp.android.presentation.components.BAButton
import com.yusuforhan.booksapp.android.presentation.components.ECErrorScreen
import com.yusuforhan.booksapp.android.presentation.theme.Secondary

@Composable
fun CartRoute(
    viewModel: CartViewModel = hiltViewModel(),
    navigateToPay: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    SideEffect {
        viewModel.getCartBooks(state.userId.orEmpty())
    }
    CartScreen(state, viewModel::handleEvent, navigateToPay)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: CartUiState,
    handleEvent: (CartUiEvent) -> Unit,
    navigateToPay: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold { innerPadding ->
        Box(modifier = modifier.padding(innerPadding), contentAlignment = Alignment.BottomEnd) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (state.loading) {
                    CircularProgressIndicator()
                } else if (state.books.isEmpty()) {
                    Text(text = stringResource(R.string.cart_list_is_empty))
                } else if (state.error.isNotBlank()) {
                    ECErrorScreen(
                        desc = state.error,
                        buttonText = stringResource(R.string.try_again)
                    )
                }

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(state.books) {
                        CartItem(book = it, handleEvent)
                    }
                }
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Column {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.total_amount), style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Secondary
                                )
                            )
                            Text(
                                text = "${state.books.calculateTotalPrice()}$",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Secondary
                                )
                            )
                        }
                        BAButton(
                            text = "Goto Pay",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = navigateToPay
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CartItem(
    book: Book,
    handleEvent: (CartUiEvent) -> Unit,
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
                            handleEvent(CartUiEvent.DeleteCart(book.id))
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