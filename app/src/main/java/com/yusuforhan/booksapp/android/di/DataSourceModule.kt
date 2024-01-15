package com.yusuforhan.booksapp.android.di

import com.yusuforhan.booksapp.android.data.source.remote.AuthService
import com.yusuforhan.booksapp.android.data.source.remote.RemoteDataSourceImpl
import com.yusuforhan.booksapp.android.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}