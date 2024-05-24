package com.eslam.data.repository.remote

import com.eslam.data.data_source.remote.CommonDataSource
import com.eslam.data.util.safeApiCall
import com.eslam.domain.repository.remote.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val commonDataSource: CommonDataSource
) : CommonRepository {
    override suspend fun countries() = safeApiCall { commonDataSource.countries() }

    override suspend fun getTerms() = safeApiCall { commonDataSource.getTerms() }
}