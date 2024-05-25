package com.eslam.domain.model

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "cast") val cast: List<String>,
    @Json(name = "year") val year: Int,
    @Json(name = "genres") val genres: List<String>,
    @Json(name = "rating") val rating: Float,
    @Json(name = "title") val title: String
)