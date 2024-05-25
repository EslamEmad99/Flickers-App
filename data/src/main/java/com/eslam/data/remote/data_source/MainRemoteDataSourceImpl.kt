package com.eslam.data.remote.data_source

import com.eslam.data.data_source.remote.MainDataSource
import com.eslam.data.remote.end_points.MainEndPoints
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val mainEndPoints: MainEndPoints
) : MainDataSource {

}