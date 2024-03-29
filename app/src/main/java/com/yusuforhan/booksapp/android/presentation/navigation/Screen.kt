package com.yusuforhan.booksapp.android.presentation.navigation


sealed class Screen(
    val route: String
) {

    data object LoginNavigation: Screen(route = "login_navigation")
    data object BooksNavigation: Screen(route = "books_navigation")

    data object NavigatorScreen : Screen(route = navigatorRoute)
    data object LoginScreen : Screen(route = loginRoute)
    data object SignUpScreen : Screen(route = signupRoute)
    data object HomeScreen : Screen(route = homeRoute)
    data object ProfileScreen : Screen(route = profileRoute)
    data object DetailScreen : Screen(route = detailRoute)
    data object CartScreen : Screen(route = cartRoute)
    data object FavoriteScreen : Screen(route = favoriteRoute)
    data object PaymentScreen : Screen(route = paymentRoute)



    companion object {
        const val bookIdKey = "bookId"
        const val navigatorRoute = "navigator_route"
        const val homeRoute = "home_route"
        const val loginRoute = "login_route"
        const val signupRoute = "signup_route"
        const val profileRoute = "profile_route"
        const val detailRoute = "detail_route"
        const val cartRoute = "cart_route"
        const val favoriteRoute = "favorite_route"
        const val paymentRoute = "payment_route"
    }
}
