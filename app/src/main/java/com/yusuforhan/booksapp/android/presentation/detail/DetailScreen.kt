package com.yusuforhan.booksapp.android.presentation.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailRoute(
    id : Int?
) {
    DetailScreen(id ?: 250)
}

@Composable
fun DetailScreen(
    id : Int
) {
    Text(text = id.toString())
}