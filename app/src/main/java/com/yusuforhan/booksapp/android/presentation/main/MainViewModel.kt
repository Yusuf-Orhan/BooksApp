package com.yusuforhan.booksapp.android.presentation.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadUserIdUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readUserIdUseCase: ReadUserIdUseCase,
) : ViewModel() {

    var startDestination by mutableStateOf(Screen.LoginNavigation.route)
        private set

    var splashCondition by mutableStateOf(true)
        private set

    init {
      getLoginState()
    }
    private fun getLoginState() {
        readUserIdUseCase().onEach { userId ->
            startDestination = if (userId != null) {
                Screen.BooksNavigation.route
            }else {
                Screen.LoginNavigation.route
            }
            delay(300)
            splashCondition = false
            Log.e("MainViewModel",userId.toString())
        }.launchIn(viewModelScope)
    }
}