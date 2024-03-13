package com.yusuforhan.booksapp.android.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.yusuforhan.booksapp.android.presentation.navigation.BooksNavHost
import com.yusuforhan.booksapp.android.presentation.navigation.Screen
import com.yusuforhan.booksapp.android.presentation.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    installSplashScreen().apply {
                        setKeepOnScreenCondition {
                            viewModel.splashCondition
                        }
                    }
                    val navHostController = rememberNavController()
                    val startDestination = viewModel.startDestination
                    BooksNavHost(
                        navHostController = navHostController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
