package com.azmetov.thecatapi.data.mapper

import com.azmetov.thecatapi.data.db.favorites.FavoriteDbModel
import com.azmetov.thecatapi.data.db.images.ImageDbModel
import com.azmetov.thecatapi.data.network.dto.ImageItemDto
import com.azmetov.thecatapi.domain.entity.CatEntity

class Mapper {

    fun mapImageDtoToDbModel(dto: ImageItemDto) = ImageDbModel(
        id = dto.id,
        url = dto.url
    )

    fun mapImageDbModelToEntity(dbModel: ImageDbModel) = CatEntity(
        id = dbModel.id,
        url = dbModel.url
    )

    fun mapFavoriteEntityToDbModel(catEntity: CatEntity) = FavoriteDbModel(
        catId = catEntity.id
    )

}