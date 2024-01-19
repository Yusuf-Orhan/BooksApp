package com.yusuforhan.booksapp.android.domain.source

interface LocalDataSource {

    suspend fun saveIsLogin(isLogin : Boolean)
    suspend fun getIsLogin() : Boolean?
}