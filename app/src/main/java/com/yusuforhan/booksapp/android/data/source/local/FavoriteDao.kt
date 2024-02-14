package com.yusuforhan.booksapp.android.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusuforhan.booksapp.android.data.model.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFromFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    fun getFavorites() : Flow<List<FavoriteEntity>>

}