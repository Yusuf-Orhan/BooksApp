package com.yusuforhan.booksapp.android.di

import android.content.Context
import com.yusuforhan.booksapp.android.data.source.local.DataStoreHelperImpl
import com.yusuforhan.booksapp.android.data.source.local.LocalDataSourceImpl
import com.yusuforhan.booksapp.android.data.source.remote.AuthService
import com.yusuforhan.booksapp.android.data.source.remote.RemoteDataSourceImpl
import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import com.yusuforhan.booksapp.android.domain.source.local.LocalDataSource
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        authService: AuthService
    ) : RemoteDataSource = RemoteDataSourceImpl(authService)

    @Provides
    @Singleton
    fun provideLocalDataSource(dataStoreHelper: DataStoreHelper) : LocalDataSource = LocalDataSourceImpl(dataStoreHelper)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context : Context) : DataStoreHelper = DataStoreHelperImpl(context)
}