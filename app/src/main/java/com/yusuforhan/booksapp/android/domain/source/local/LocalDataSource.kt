package com.yusuforhan.booksapp.android.domain.source.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveIsLogin(isLogin : Boolean)
    suspend fun getIsLogin() : Flow<Boolean?>
}