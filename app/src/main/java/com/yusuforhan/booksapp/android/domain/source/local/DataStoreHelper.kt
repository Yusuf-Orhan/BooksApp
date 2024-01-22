package com.yusuforhan.booksapp.android.domain.source.local

import kotlinx.coroutines.flow.Flow

interface DataStoreHelper {

    var isLogin: Flow<Boolean>
    suspend fun saveLoginState(state: Boolean)
}