package com.yusuforhan.booksapp.android.data.source.local

import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import com.yusuforhan.booksapp.android.domain.source.local.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStore : DataStoreHelper
): LocalDataSource  {
    override suspend fun saveIsLogin() = dataStore.saveLoginState()


    override  fun getIsLogin(): Flow<Boolean> {
        return dataStore.readLoginState()
    }
}