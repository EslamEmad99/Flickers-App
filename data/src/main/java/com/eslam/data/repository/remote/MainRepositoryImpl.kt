package com.eslam.data.repository.remote

import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.data.util.safeApiCall
import com.eslam.domain.repository.remote.MainRepository
import javax.inject.Inject

/**
 * A repository acts as a mediator between the data sources (e.g., local database, network) and the domain layer.
 * It abstracts the logic for accessing and managing data, providing a clean interface for the domain layer
 * to interact with data without being concerned about the underlying data sources.
 * Repositories help centralize data access logic, promote code reusability, and improve testability by
 * decoupling the domain layer from specific data source implementations.
 * **/
class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {
    override suspend fun getMovieFlickers(movieTitle: String) = safeApiCall {
        mainDataSource.getMovieFlickers(movieTitle)
    }
}