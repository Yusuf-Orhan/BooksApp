package com.yusuforhan.booksapp.android.data.source.local

import com.yusuforhan.booksapp.android.domain.source.local.LocalDataSource

class LocalDataSourceImpl : LocalDataSource{
    override suspend fun saveIsLogin(isLogin: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getIsLogin(): Boolean? {
        TODO("Not yet implemented")
    }
}