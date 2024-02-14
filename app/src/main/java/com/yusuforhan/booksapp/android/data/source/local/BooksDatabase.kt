package com.yusuforhan.booksapp.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteDao::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}