package com.yusuforhan.booksapp.android.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.favorite.viewmodel.FavoriteViewModel

@Composable
fun FavoriteRoute() {
    val viewModel : FavoriteViewModel = hiltViewModel()
    FavoriteScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier : Modifier = Modifier
) {
    Scaffold (
        topBar = { CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.favorite)) })}
    ){
        Column(
            modifier = modifier.padding(it)
        ) {

        }
    }

}
