package com.example.loginsignupapp.network

import okhttp3.ResponseBody

sealed class Resourse<out T> {
    data class  Success<out T>(val value: T): Resourse<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ): Resourse<Nothing>()
}