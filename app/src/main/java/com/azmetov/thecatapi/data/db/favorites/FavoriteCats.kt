package com.azmetov.thecatapi.data.db.favorites

import androidx.room.Embedded
import androidx.room.Relation
import com.azmetov.thecatapi.data.db.images.ImageDbModel

data class FavoriteCats(
    @Embedded
    val favoriteDbModel: FavoriteDbModel,
    @Relation(
        parentColumn = "catId",
        entityColumn = "id"
    )
    val imageDbModel: ImageDbModel? = null
)
