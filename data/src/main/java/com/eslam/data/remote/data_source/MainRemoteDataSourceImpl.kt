package com.eslam.data.remote.data_source

import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.data.remote.end_points.MainEndPoints
import javax.inject.Inject

/**
 * A data source is needed to abstract the source of data from the domain and presentation layers.
 * It allows the application to fetch data from various sources such as a local database, network,
 * or remote API without tightly coupling these implementations to the domain logic.
 * By separating the data source from the domain logic, we achieve better modularity and
 * maintainability, making it easier to switch or extend data sources in the future.
 * **/
class MainRemoteDataSourceImpl @Inject constructor(
    private val mainEndPoints: MainEndPoints
) : MainDataSource {
    override suspend fun getMovieFlickers(movieTitle: String) =
        mainEndPoints.getMovieFlickers(movieTitle = movieTitle)
}