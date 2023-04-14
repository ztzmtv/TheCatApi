package com.azmetov.thecatapi.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.azmetov.thecatapi.data.db.favorites.FavoritesDao
import com.azmetov.thecatapi.data.db.images.ImagesDao
import com.azmetov.thecatapi.data.mapper.Mapper
import com.azmetov.thecatapi.data.paging.ImageRemoteMediator
import com.azmetov.thecatapi.domain.entity.ImageEntity
import com.azmetov.thecatapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RepositoryImpl(
    private val imagesDao: ImagesDao,
    private val favoritesDao: FavoritesDao,
    private val mediator: ImageRemoteMediator,
    private val mapper: Mapper
) : Repository {

    override fun getImages(): Flow<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10 // not required, may be deleted
            ),
            remoteMediator = mediator,
            pagingSourceFactory = { imagesDao.getPagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    mapper.mapImageDbModelToEntity(it)
                }
            }
    }

    override suspend fun saveToFavorites(imageEntity: ImageEntity) {
        val dbModel = mapper.mapFavoriteEntityToDbModel(imageEntity)
        favoritesDao.saveFavorite(dbModel)
    }

    override suspend fun getFavorites(): List<ImageEntity> {
        val dbModelList = favoritesDao.getFavorites()
        val result = mutableListOf<ImageEntity>()
        dbModelList.forEach {
            result.add(mapper.mapFavoriteDbModelToEntity(it))
        }
        return result
    }

    override suspend fun deleteFavorite(imageEntity: ImageEntity) {
        favoritesDao.deleteFavorite(imageEntity.id)
    }
}