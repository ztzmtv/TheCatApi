package com.azmetov.thecatapi.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageDbModel(
    @PrimaryKey
    val id: String,
    val url: String,
)