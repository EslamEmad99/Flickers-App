package com.eslam.data.data_source.remote

import com.eslam.domain.model.FlickersData

interface MainDataSource {

    suspend fun getMovieFlickers(movieTitle: String): FlickersData
}