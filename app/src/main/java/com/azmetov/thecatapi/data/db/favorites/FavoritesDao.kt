package com.azmetov.thecatapi.data.db.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Transaction
    @Query("SELECT * FROM images")
    fun getFavoritesCats(): Flow<List<FavoriteCats>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteDbModel: FavoriteDbModel)

    @Query("DELETE FROM favorites WHERE catId == :id")
    suspend fun deleteFavorite(id: String)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteDbModel>

}