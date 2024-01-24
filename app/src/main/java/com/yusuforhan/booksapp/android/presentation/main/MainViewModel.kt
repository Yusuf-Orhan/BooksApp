package com.yusuforhan.booksapp.android.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.domain.usecase.GetLoginStateUseCase
import com.yusuforhan.booksapp.android.presentation.auth.login.loginRoute
import com.yusuforhan.booksapp.android.presentation.home.homeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoginStateUseCase: GetLoginStateUseCase,
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination : StateFlow<String?> = _startDestination

    init {
        getLoginState()
    }
    private fun getLoginState() = viewModelScope.launch {
        getLoginStateUseCase().collect {
            _startDestination.value = if (it == true) homeRoute else loginRoute
            Log.e("MainViewModel",it.toString() ?: "Null")
        }
    }

}