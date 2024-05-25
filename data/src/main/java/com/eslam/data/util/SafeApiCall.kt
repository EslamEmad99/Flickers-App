package com.eslam.data.util

import com.eslam.data.util.FailRequestCode.BLOCKED
import com.eslam.data.util.FailRequestCode.EXCEPTION
import com.eslam.data.util.FailRequestCode.FAIL
import com.eslam.data.util.FailRequestCode.UN_AUTH
import com.eslam.data.util.NetworkConstants.NETWORK_TIMEOUT
import com.google.gson.Gson
import com.eslam.domain.exceptions.NetworkExceptions
import com.eslam.domain.model.base.BaseResponse
import com.eslam.domain.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Flow<DataState<T>> = flow {
    withTimeout(NETWORK_TIMEOUT) {
        val response = apiCall.invoke()
        emit(handleSuccess(response))
    }
}.onStart {
    emit(DataState.Loading)
}.catch {
    emit(handleError(it))
}.flowOn(Dispatchers.IO)

fun <T> handleSuccess(response: T): DataState<T> {
    if (response != null) return DataState.Success(response)
    return DataState.Error(NetworkExceptions.UnknownException)
}

fun <T> handleError(it: Throwable): DataState<T> {
    it.printStackTrace()
    return when (it) {
        is TimeoutCancellationException -> {
            DataState.Error(NetworkExceptions.TimeoutException)
        }

        is UnknownHostException -> {
            DataState.Error(NetworkExceptions.ConnectionException)
        }

        is IOException -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }

        is HttpException -> {
            DataState.Error(convertErrorBody(it))
        }

        else -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }
    }
}

private fun convertErrorBody(throwable: HttpException): Exception {
    val errorBody = throwable.response()?.errorBody()?.charStream()
    val response = Gson().fromJson(errorBody, BaseResponse::class.java)
    return when (throwable.code()) {
        FAIL -> NetworkExceptions.CustomException(response.msg)
        UN_AUTH, BLOCKED -> NetworkExceptions.AuthorizationException
        EXCEPTION -> NetworkExceptions.ServerException
        else -> NetworkExceptions.UnknownException
    }
}