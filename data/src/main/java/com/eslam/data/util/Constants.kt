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
    const val COUNTRIES = "countries"
    const val TERMS = "terms"
}

object CommonRemoteEndPointsParameters {
    const val METHOD_TYPE = "_method"
    const val PATCH = "patch"
}