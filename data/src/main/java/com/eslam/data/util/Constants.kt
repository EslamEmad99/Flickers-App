package com.eslam.data.util

object Languages {
    const val ARABIC = "ar"
    const val ENGLISH = "en"
}

object NetworkConstants {
    const val LANGUAGE = "lang"
    const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
    const val NETWORK_TIMEOUT: Long = 600000000
    const val API_VERSION = "/api/"
    const val TYPE_ANDROID = "android"
    const val METHOD_NAME = "flickr.photos.search"
    const val NO_JSON_CALLBACK_VALUE = 1
    const val JSON = "json"
    const val FLICKER_API_KEY = "29e46f88ce72ce174b061d3357e9da19"
    const val PER_PAGE_VALUE = 50
}

object FailRequestCode {
    const val FAIL = 400
    const val UN_AUTH = 419
    const val BLOCKED = 423
    const val EXCEPTION = 500
}

object PreferenceConstants {
    const val PREFERENCE_NAME = "base_preference"
    const val LANGUAGE = "language"
    const val TOKEN = "token"
    const val USER_DATA = "user_data"
    const val IS_LOGIN = "is_login"
    const val USER_TYPE = "user_type"
}

object CommonRemoteEndPointsNames {
    const val REST = "rest"
}

object CommonRemoteEndPointsParameters {
    const val TEXT = "text"
    const val METHOD = "method"
    const val NO_JSON_CALLBACK = "nojsoncallback"
    const val FORMAT = "format"
    const val API_KEY = "api_key"
    const val PAGE = "page"
    const val PER_PAGE = "per_page"
}