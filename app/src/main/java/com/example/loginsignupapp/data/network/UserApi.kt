package com.example.loginsignupapp.data.network

import com.example.loginsignupapp.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser():LoginResponse
}