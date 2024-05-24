package com.eslam.domain.model

import com.squareup.moshi.Json

data class DataUser(
    @Json(name = "user") val user: User
)
