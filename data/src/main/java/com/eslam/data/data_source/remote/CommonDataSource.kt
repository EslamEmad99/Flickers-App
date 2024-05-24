package com.eslam.data.data_source.remote

import com.eslam.domain.model.Country
import com.eslam.domain.model.base.BaseResponse

interface CommonDataSource {

    suspend fun countries(): BaseResponse<List<Country>>

    suspend fun getTerms(): BaseResponse<String>
}