package com.yusuforhan.booksapp.android.domain.source.local

import kotlinx.coroutines.flow.Flow

interface DataStoreHelper {

    suspend fun saveLoginState(state: Boolean)
    suspend fun getLoginState() : Flow<Boolean>
}