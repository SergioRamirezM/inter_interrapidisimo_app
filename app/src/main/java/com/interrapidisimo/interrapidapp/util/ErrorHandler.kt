package com.interrapidisimo.interrapidapp.util

import com.interrapidisimo.interrapidapp.data.model.ApiError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {

    fun parseError(throwable: Throwable): ApiError {
        return when (throwable) {
            is HttpException -> {
                val code = throwable.code()
                val status = when (code) {
                    400 -> ApiError.ErrorStatus.BAD_REQUEST
                    401 -> ApiError.ErrorStatus.UNAUTHORIZED
                    403 -> ApiError.ErrorStatus.FORBIDDEN
                    404 -> ApiError.ErrorStatus.NOT_FOUND
                    405 -> ApiError.ErrorStatus.METHOD_NOT_ALLOWED
                    409 -> ApiError.ErrorStatus.CONFLICT
                    500 -> ApiError.ErrorStatus.INTERNAL_SERVER_ERROR
                    503 -> ApiError.ErrorStatus.SERVICE_UNAVAILABLE
                    else -> ApiError.ErrorStatus.UNKNOWN_ERROR
                }
                ApiError("Código HTTP: $code", code, status)
            }

            is SocketTimeoutException -> ApiError(null, ApiError.ErrorStatus.TIMEOUT)
            is UnknownHostException -> ApiError(null, ApiError.ErrorStatus.NO_CONNECTION)
            is IOException -> ApiError(null, ApiError.ErrorStatus.IOEXCEPTION_UNKNOWN_ERROR)
            else -> ApiError(null, ApiError.ErrorStatus.UNKNOWN_ERROR)
        }
    }
}