package com.yusuforhan.booksapp.android.presentation.favorite

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen

fun NavGraphBuilder.favoriteScreen(
    navigateUp: () -> Unit,
    navigateToDetail : (Int) -> Unit
) {
    composable(
        route = Screen.favoriteRoute
    ) {
        FavoriteRoute(navigateToDetail)
    }
}

fun NavHostController.navigateToFavorite(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Screen.favoriteRoute
    )
}