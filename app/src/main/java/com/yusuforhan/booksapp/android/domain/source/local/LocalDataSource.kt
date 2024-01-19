package com.yusuforhan.booksapp.android.domain.source.local

interface LocalDataSource {

    suspend fun saveIsLogin(isLogin : Boolean)
    suspend fun getIsLogin() : Boolean?
}