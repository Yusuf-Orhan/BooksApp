package com.yusuforhan.booksapp.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yusuforhan.booksapp.android.common.TypeConverter
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}