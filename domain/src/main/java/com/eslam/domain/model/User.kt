package com.eslam.domain.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "country_code") val countryCode: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "full_phone") val fullPhone: String,
    @Json(name = "image") val image: String,
    @Json(name = "lang") val lang: String,
    @Json(name = "is_notify") val isNotify: Boolean,
    @Json(name = "token") val token: String
)