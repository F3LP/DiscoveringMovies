package com.facd.discoveringmovies.data.util

import com.facd.discoveringmovies.domain.model.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.response()?.errorBody()?.toString()
                    val gsonErrorBody = Gson().fromJson(
                        errorResponse,
                        ErrorResponse::class.java
                    )
                    val message = gsonErrorBody.message
                    ResultWrapper.GenericError(code, message)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int?, val message: String?) : ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}