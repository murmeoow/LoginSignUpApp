package com.example.loginsignupapp.network

import com.example.loginsignupapp.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}