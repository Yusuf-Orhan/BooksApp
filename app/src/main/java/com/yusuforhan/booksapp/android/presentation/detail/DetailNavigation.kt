package com.yusuforhan.booksapp.android.presentation.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusuforhan.booksapp.android.presentation.navigation.Screen
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.bookIdKey
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.detailRoute


fun NavGraphBuilder.detailScreen() {
    composable(
        route = detailRoute.plus("/{${bookIdKey}}"),
        arguments = listOf(
            navArgument(bookIdKey, builder = {type = NavType.IntType})
        )
    ) {
        DetailRoute(it.arguments?.getInt(bookIdKey))
    }
}

fun NavHostController.navigateToDetail(
    id : Int,
    navOptions: NavOptions? = null
) {
    navigate(
        route = Screen.DetailScreen.route.plus("/$id"),
        navOptions = navOptions
    )
}