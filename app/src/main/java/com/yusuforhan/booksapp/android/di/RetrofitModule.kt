package com.yusuforhan.booksapp.android.di

import com.yusuforhan.booksapp.android.common.Constants.BASE_URL
import com.yusuforhan.booksapp.android.data.source.remote.AuthService
import com.yusuforhan.booksapp.android.data.source.remote.RemoteDataSourceImpl
import com.yusuforhan.booksapp.android.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideAuthService(): AuthService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthService::class.java)


    @Provides
    @Singleton
    fun provideRemoteDataSource(
        authService: AuthService
    ) : RemoteDataSource = RemoteDataSourceImpl(authService)

}