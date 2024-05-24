package com.eslam.domain.repository.remote

import com.eslam.domain.model.Country
import com.eslam.domain.model.base.BaseResponse
import com.eslam.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface CommonRepository {

    suspend fun countries(): Flow<DataState<BaseResponse<List<Country>>>>

    suspend fun getTerms(): Flow<DataState<BaseResponse<String>>>
}