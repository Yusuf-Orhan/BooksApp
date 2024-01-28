package com.yusuforhan.booksapp.android.presentation.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.domain.usecase.auth.ReadIsLoginUseCase
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.homeRoute
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.loginRoute
import com.yusuforhan.booksapp.android.presentation.navigation.Screen.Companion.navigatorRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readIsLoginUseCase: ReadIsLoginUseCase,
) : ViewModel() {

    var startDestination by mutableStateOf(loginRoute)
        private set
    init {
      getLoginState()
    }
    private fun getLoginState() {
        readIsLoginUseCase().onEach {isLogin ->
            startDestination = if (isLogin) {
                navigatorRoute
            }else {
                loginRoute
            }
            Log.e("MainViewModel",isLogin.toString() ?: "Null")
        }.launchIn(viewModelScope)
    }
}