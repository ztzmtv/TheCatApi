package com.azmetov.thecatapi.data.mapper

import com.azmetov.thecatapi.data.db.ImageDbModel
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

}