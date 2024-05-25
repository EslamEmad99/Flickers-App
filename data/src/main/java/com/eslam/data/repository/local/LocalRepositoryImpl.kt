package com.eslam.data.repository.local

import com.eslam.data.data_source.local.LocalDataSource
import com.eslam.domain.repository.local.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {
    override suspend fun getMovies() = localDataSource.getMovies().movies
}