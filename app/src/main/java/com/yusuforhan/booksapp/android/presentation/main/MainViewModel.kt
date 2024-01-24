package com.yusuforhan.booksapp.android.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuforhan.booksapp.android.domain.usecase.GetLoginStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoginStateUseCase: GetLoginStateUseCase,
) : ViewModel() {

    val loginState = MutableStateFlow(false)

    fun getLoginState() = viewModelScope.launch {
        getLoginStateUseCase().collect {
            loginState.value = it ?: true
            Log.e("MainViewModel",it.toString() ?: "Null")
        }
    }

}