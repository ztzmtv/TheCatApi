package com.azmetov.thecatapi.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.azmetov.thecatapi.data.db.ImagesDao
import com.azmetov.thecatapi.data.mapper.Mapper
import com.azmetov.thecatapi.data.paging.ImageRemoteMediator
import com.azmetov.thecatapi.domain.entity.ImageEntity
import com.azmetov.thecatapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RepositoryImpl(
    private val dao: ImagesDao,
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
            pagingSourceFactory = { dao.getPagingSource() }
        ).flow
            .map { pagindData ->
                pagindData.map {
                    mapper.mapImageDbModelToEntity(it)
                }
            }
    }
}