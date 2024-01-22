package com.yusuforhan.booksapp.android.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.yusuforhan.booksapp.android.presentation.auth.login.loginRoute
import com.yusuforhan.booksapp.android.presentation.home.homeRoute
import com.yusuforhan.booksapp.android.presentation.navigation.BooksNavHost
import com.yusuforhan.booksapp.android.ui.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLoginState()
        setContent {
            BooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val isLogin = viewModel.loginState.collectAsState()
                    val navHostController = rememberNavController()
                    val startDestination = if (isLogin.value) homeRoute else loginRoute
                    BooksNavHost(navHostController = navHostController, startDestination = startDestination)
                }
            }
        }
    }
}
