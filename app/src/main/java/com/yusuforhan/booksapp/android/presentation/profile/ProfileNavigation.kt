package com.yusuforhan.booksapp.android.presentation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.profileRoute

fun NavGraphBuilder.profileScreen() {
    composable(
        route = profileRoute
    ) {
        ProfileRoute()
    }
}