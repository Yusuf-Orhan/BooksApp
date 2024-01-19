package com.yusuforhan.booksapp.android.domain.source.local

interface DataStoreHelper {

    suspend fun saveLoginState(state : Boolean)
    suspend fun getLoginState() : Boolean?

}