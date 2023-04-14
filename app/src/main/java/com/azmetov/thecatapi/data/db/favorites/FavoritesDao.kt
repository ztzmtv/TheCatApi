package com.azmetov.thecatapi.data.db.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteDbModel: FavoriteDbModel)

    @Query("DELETE FROM favorites WHERE id == :id")
    suspend fun deleteFavorite(id: String)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteDbModel>

}