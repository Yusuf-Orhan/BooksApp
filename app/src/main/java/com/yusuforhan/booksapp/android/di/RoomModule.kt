package com.yusuforhan.booksapp.android.di

import android.content.Context
import androidx.room.Room
import com.yusuforhan.booksapp.android.common.Constants.DATABASE_NAME
import com.yusuforhan.booksapp.android.data.source.local.BooksDatabase
import com.yusuforhan.booksapp.android.data.source.local.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context : Context)  = Room.databaseBuilder(context,
        BooksDatabase::class.java,DATABASE_NAME).build()
    @Provides
    @Singleton
    fun provideFavoriteDao(database: BooksDatabase) : FavoriteDao = database.favoriteDao()
}