package com.azmetov.thecatapi.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ImagesDao {
    @Query("SELECT * FROM images")
    suspend fun getBreweriesList(): List<ImageDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImage(imageDbModel: ImageDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveImageList(imageList: List<ImageDbModel>)

    @Transaction
    suspend fun refreshImageList(imageList: List<ImageDbModel>) {
        clear()
        saveImageList(imageList)
    }

    @Query("SELECT * FROM images")
    fun getPagingSource(): PagingSource<Int, ImageDbModel>

    @Query("DELETE FROM images")
    suspend fun clear()


}