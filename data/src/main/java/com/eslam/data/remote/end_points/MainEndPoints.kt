package com.eslam.data.remote.end_points

import com.eslam.data.util.CommonRemoteEndPointsNames.REST
import com.eslam.data.util.CommonRemoteEndPointsParameters.API_KEY
import com.eslam.data.util.CommonRemoteEndPointsParameters.FORMAT
import com.eslam.data.util.CommonRemoteEndPointsParameters.METHOD
import com.eslam.data.util.CommonRemoteEndPointsParameters.NO_JSON_CALLBACK
import com.eslam.data.util.CommonRemoteEndPointsParameters.PAGE
import com.eslam.data.util.CommonRemoteEndPointsParameters.PER_PAGE
import com.eslam.data.util.CommonRemoteEndPointsParameters.TEXT
import com.eslam.data.util.NetworkConstants.FLICKER_API_KEY
import com.eslam.data.util.NetworkConstants.JSON
import com.eslam.data.util.NetworkConstants.METHOD_NAME
import com.eslam.data.util.NetworkConstants.NO_JSON_CALLBACK_VALUE
import com.eslam.data.util.NetworkConstants.PER_PAGE_VALUE
import com.eslam.domain.model.FlickersData
import retrofit2.http.GET
import retrofit2.http.Query

interface MainEndPoints {

    @GET(REST)
    suspend fun getMovieFlickers(
        @Query(TEXT) movieTitle: String,
        @Query(METHOD) method: String = METHOD_NAME,
        @Query(NO_JSON_CALLBACK) noJsonCallback: Int = NO_JSON_CALLBACK_VALUE,
        @Query(FORMAT) format: String = JSON,
        @Query(API_KEY) apiKey: String = FLICKER_API_KEY,
        @Query(PAGE) page: Int = 1,
        @Query(PER_PAGE) perPage: Int = PER_PAGE_VALUE
    ): FlickersData
}