package com.yusuforhan.booksapp.android.common

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yusuforhan.booksapp.android.data.model.remote.Book

class TypeConverter  {
    private val gson = Gson()

    @TypeConverter
    fun fromBooks(item : Book) : String {
        return gson.toJson(item)
    }

    @TypeConverter
    fun toBooks(itemString: String) : Book {
        return gson.fromJson(itemString,Book::class.java)
    }
}