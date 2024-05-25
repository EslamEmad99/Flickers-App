package com.eslam.domain.repository.remote

import com.eslam.domain.model.FlickersData
import com.eslam.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getMovieFlickers(movieTitle: String): Flow<DataState<FlickersData>>
}