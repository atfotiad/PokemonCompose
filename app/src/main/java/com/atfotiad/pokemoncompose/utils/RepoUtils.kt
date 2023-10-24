package com.atfotiad.pokemoncompose.utils

import retrofit2.Response

suspend fun <T> (suspend () -> Response<T>).toResult(): Result<T> {
    return try {
        val response = invoke()
        response.toResult()
    } catch (t: Throwable) {
        Result.failure(t)
    }
}


fun <T> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            val body = body()!!
            Result.success(body)
        } else {
            throw Exception()
        }
    } catch (t: Throwable) {
        Result.failure(t)
    }
}