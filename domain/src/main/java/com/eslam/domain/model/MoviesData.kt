package com.eslam.domain.model

import com.squareup.moshi.Json

data class MoviesData(
    @Json(name = "movies") val movies: ArrayList<Movie>
)