package com.azmetov.thecatapi.data.db.favorites

import androidx.room.Embedded
import androidx.room.Relation
import com.azmetov.thecatapi.data.db.images.ImageDbModel

data class FavoriteCats(
    @Embedded
    val imageDbModel: ImageDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "catId"
    )
    val favoriteDbModel: FavoriteDbModel
)
