package com.yusuforhan.booksapp.android.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusuforhan.booksapp.android.data.model.remote.Books

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val books: Books
)