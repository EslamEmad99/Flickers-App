package com.eslam.data.remote.data_source

import com.eslam.data.data_source.remote.CommonDataSource
import com.eslam.data.remote.end_points.CommonEndPoints
import javax.inject.Inject

class CommonRemoteDataSourceImpl @Inject constructor(
    private val commonEndPoints: CommonEndPoints
) : CommonDataSource {

    override suspend fun countries() = commonEndPoints.countries()

    override suspend fun getTerms() = commonEndPoints.getTerms()
}