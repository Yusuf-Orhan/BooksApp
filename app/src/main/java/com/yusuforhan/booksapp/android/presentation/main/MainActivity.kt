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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.yusuforhan.booksapp.android.presentation.auth.login.loginRoute
import com.yusuforhan.booksapp.android.presentation.home.homeRoute
import com.yusuforhan.booksapp.android.presentation.navigation.BooksNavHost
import com.yusuforhan.booksapp.android.presentation.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val startDestination = viewModel.startDestination.collectAsStateWithLifecycle()
                    Log.e("MainActivity", "Start Destination : $startDestination")
                    val navHostController = rememberNavController()
                    BooksNavHost(
                        navHostController = navHostController,
                        startDestination = startDestination.value ?: loginRoute
                    )
                }
            }
        }
    }
}
