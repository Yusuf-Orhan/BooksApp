package com.yusuforhan.booksapp.android.presentation.profile.viewmodel

sealed class ProfileUiEvent {
    data object SignOut : ProfileUiEvent()
}