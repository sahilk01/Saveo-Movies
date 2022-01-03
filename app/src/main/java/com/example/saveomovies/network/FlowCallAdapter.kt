package com.example.saveomovies.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.*
import java.lang.reflect.Type

class FlowCallAdapter<T>(
    private val responseType: Type,
) : CallAdapter<T, Flow<T>> {

    override fun responseType() = responseType

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<T>): Flow<T> = flow {
        val response: Response<T> = call.awaitResponse()
        emit(response.bodyOrThrow())
    }
}

fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}