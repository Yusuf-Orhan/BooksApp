package com.yusuforhan.booksapp.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yusuforhan.booksapp.android.R
import com.yusuforhan.booksapp.android.presentation.cart.cartScreen
import com.yusuforhan.booksapp.android.presentation.detail.detailScreen
import com.yusuforhan.booksapp.android.presentation.detail.navigateToDetail
import com.yusuforhan.booksapp.android.presentation.favorite.favoriteScreen
import com.yusuforhan.booksapp.android.presentation.home.homeScreen
import com.yusuforhan.booksapp.android.presentation.navigation.components.BooksBottomNav
import com.yusuforhan.booksapp.android.presentation.navigation.components.BottomNavigationItem
import com.yusuforhan.booksapp.android.presentation.pay.navigateToPayScreen
import com.yusuforhan.booksapp.android.presentation.pay.paymentScreen
import com.yusuforhan.booksapp.android.presentation.profile.profileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksNavigator() {
    val navController = rememberNavController()
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_favorite_border_24, text = "Favorite"),
            BottomNavigationItem(icon = R.drawable.ic_shopping_cart, text = "Cart"),
            BottomNavigationItem(icon = R.drawable.ic_profile, text = "Profile")
        )
    }
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Screen.HomeScreen.route -> 0
            Screen.FavoriteScreen.route -> 1
            Screen.CartScreen.route -> 2
            Screen.ProfileScreen.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Screen.HomeScreen.route || backstackState?.destination?.route == Screen.ProfileScreen.route || backstackState?.destination?.route == Screen.CartScreen.route || backstackState?.destination?.route == Screen.FavoriteScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            BooksBottomNav(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTap(
                            navController = navController, route = Screen.homeRoute
                        )

                        1 -> navigateToTap(
                            navController = navController, route = Screen.favoriteRoute
                        )
                        2 -> navigateToTap(
                            navController = navController, route = Screen.cartRoute
                        )
                        3 -> navigateToTap(
                            navController = navController, route = Screen.profileRoute
                        )
                    }
                }
            )
        }
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController, startDestination = Screen.HomeScreen.route
            ) {
                homeScreen(
                    navigateToDetail = navController::navigateToDetail
                )
                profileScreen()
                detailScreen(navController::navigateUp)
                cartScreen(navigateToPay = navController::navigateToPayScreen)
                favoriteScreen(navController::navigateUp,navController::navigateToDetail)
                paymentScreen(navigateUp = navController::navigateUp)
            }
        }
    }
}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}