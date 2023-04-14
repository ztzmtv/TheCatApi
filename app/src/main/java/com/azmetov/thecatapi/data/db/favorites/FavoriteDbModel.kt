package com.azmetov.thecatapi.data.db.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteDbModel(
    @PrimaryKey
    val id: String,
    val url: String,
)
