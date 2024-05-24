package com.eslam.domain.model

import com.squareup.moshi.Json

data class Country(
    @Json(name = "id") val id: Int,
    @Json(name = "flag") val flag: String,
    @Json(name = "name") val name: String,
    @Json(name = "key") val key: String
)