package com.eslam.domain.model

import com.squareup.moshi.Json

data class FlickersData(
    @Json(name = "stat") val stat: String,
    @Json(name = "code") val code: Int?,
    @Json(name = "message") val message: String?,
    @Json(name = "photos") val flickersPhotosData: FlickersPhotosData?
)

data class FlickersPhotosData(
    @Json(name = "perpage") val perPage: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "photo") val photo: List<Flicker>?
)

data class Flicker(
    @Json(name = "owner") val owner: String,
    @Json(name = "server") val server: String,
    @Json(name = "ispublic") val isPublic: Int,
    @Json(name = "isfriend") val isFriend: Int,
    @Json(name = "farm") val farm: Int,
    @Json(name = "id") val id: String,
    @Json(name = "secret") val secret: String,
    @Json(name = "title") val title: String,
    @Json(name = "isfamily") val isFamily: Int
)