package com.yusuforhan.booksapp.android.di

import com.yusuforhan.booksapp.android.data.repository.AuthRepositoryImpl
import com.yusuforhan.booksapp.android.data.repository.BooksRepositoryImpl
import com.yusuforhan.booksapp.android.data.repository.CartRepositoryImpl
import com.yusuforhan.booksapp.android.domain.repository.AuthRepository
import com.yusuforhan.booksapp.android.domain.repository.BooksRepository
import com.yusuforhan.booksapp.android.domain.repository.CartRepository
import com.yusuforhan.booksapp.android.domain.source.local.DataStoreHelper
import com.yusuforhan.booksapp.android.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: RemoteDataSource,dataStoreHelper: DataStoreHelper) : AuthRepository = AuthRepositoryImpl(dataSource,dataStoreHelper)


    @Provides
    @Singleton
    fun provideBooksRepository(dataSource: RemoteDataSource) : BooksRepository = BooksRepositoryImpl(dataSource)
    @Provides
    @Singleton
    fun provideCartRepository(dataSource: RemoteDataSource) : CartRepository = CartRepositoryImpl(dataSource)
}