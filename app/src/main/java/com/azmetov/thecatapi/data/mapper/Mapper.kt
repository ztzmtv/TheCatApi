package com.azmetov.thecatapi.data.mapper

import com.azmetov.thecatapi.data.db.favorites.FavoriteDbModel
import com.azmetov.thecatapi.data.db.images.ImageDbModel
import com.azmetov.thecatapi.data.network.dto.ImageItemDto
import com.azmetov.thecatapi.domain.entity.ImageEntity

class Mapper {

    fun mapImageDtoToDbModel(dto: ImageItemDto) = ImageDbModel(
        id = dto.id,
        url = dto.url
    )

    fun mapImageDbModelToEntity(dbModel: ImageDbModel) = ImageEntity(
        id = dbModel.id,
        url = dbModel.url
    )

    fun mapFavoriteEntityToDbModel(imageEntity: ImageEntity) = FavoriteDbModel(
        id = imageEntity.id,
        url = imageEntity.url
    )

    fun mapFavoriteDbModelToEntity(favoriteDbModel: FavoriteDbModel) = ImageEntity(
        id = favoriteDbModel.id,
        url = favoriteDbModel.url
    )

}