package com.yusuforhan.booksapp.android.domain.source.local

import kotlinx.coroutines.flow.Flow

interface DataStoreHelper {

    suspend fun saveLoginState(isLogin : Boolean)
    fun readLoginState() : Flow<Boolean>
}