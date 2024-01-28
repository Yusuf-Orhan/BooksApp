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

    companion object {
        const val navigatorRoute = "navigator_route"
        const val homeRoute = "home_route"
        const val loginRoute = "login_route"
        const val signupRoute = "signup_route"
        const val profileRoute = "profile_route"
    }
}
