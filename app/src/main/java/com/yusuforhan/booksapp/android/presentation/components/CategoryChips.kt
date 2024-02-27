package com.yusuforhan.booksapp.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesChips(
    categories : List<String>,
    onItemClick : (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        items(categories.size) {
            Text(
                modifier = Modifier
                    .padding(3.dp)
                    .clickable { onItemClick(it) },
                text = categories[it],
            )
        }
    }
}