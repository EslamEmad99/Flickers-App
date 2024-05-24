package com.eslam.data.remote.end_points

import com.eslam.data.util.CommonRemoteEndPointsNames.COUNTRIES
import com.eslam.data.util.CommonRemoteEndPointsNames.TERMS
import com.eslam.domain.model.Country
import com.eslam.domain.model.base.BaseResponse
import retrofit2.http.GET

interface CommonEndPoints {

    @GET(COUNTRIES)
    suspend fun countries(): BaseResponse<List<Country>>

    @GET(TERMS)
    suspend fun getTerms(): BaseResponse<String>

}