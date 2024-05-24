package com.eslam.domain.exceptions

sealed class LocalExceptions : Exception() {
    data object UnknownException : LocalExceptions()
    data object TimeoutException : LocalExceptions()
}