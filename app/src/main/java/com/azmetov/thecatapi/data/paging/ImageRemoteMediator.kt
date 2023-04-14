package com.azmetov.thecatapi.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.azmetov.thecatapi.data.db.images.ImageDbModel
import com.azmetov.thecatapi.data.db.images.ImagesDao
import com.azmetov.thecatapi.data.mapper.Mapper
import com.azmetov.thecatapi.data.network.core.ImagesApi

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val dao: ImagesDao,
    private val api: ImagesApi,
    private val mapper: Mapper
) : RemoteMediator<Int, ImageDbModel>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDbModel>
    ): MediatorResult {
        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val images = fetchLaunches(limit, offset)
            if (loadType == LoadType.REFRESH) {
                dao.refreshImageList(images)
            } else {
                dao.saveImageList(images)
            }
            MediatorResult.Success(
                endOfPaginationReached = images.size < limit
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun fetchLaunches(
        limit: Int,
        offset: Int
    ): List<ImageDbModel> {
        val response = api.search(page = offset, limit = limit)
        return response.map { dto ->
            mapper.mapImageDtoToDbModel(dto)
        }
    }
}