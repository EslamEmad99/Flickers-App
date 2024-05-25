package com.eslam.data.data_source.local

import com.eslam.domain.model.MoviesData

interface LocalDataSource {
    suspend fun getMovies(): MoviesData
}