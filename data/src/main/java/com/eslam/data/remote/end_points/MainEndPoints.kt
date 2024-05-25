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

    // This API to get the movie flicker images depending on the movie title
    // The movie title is passed as a query parameter
    // Retrofit is the responsible of the implementation of the API
    @GET(REST)
    suspend fun getMovieFlickers(
        @Query(TEXT) movieTitle: String, // The MovieTitle to search
        @Query(METHOD) method: String = METHOD_NAME, // The method to communicate with the Flicker API
        @Query(NO_JSON_CALLBACK) noJsonCallback: Int = NO_JSON_CALLBACK_VALUE, // To get the data in JSON format
        @Query(FORMAT) format: String = JSON, // The format of the response
        @Query(API_KEY) apiKey: String = FLICKER_API_KEY, // The API key to communicate with the Flicker API
        @Query(PAGE) page: Int = 1, // To get the first page
        @Query(PER_PAGE) perPage: Int = PER_PAGE_VALUE // to get 50 images per page
    ): FlickersData
}