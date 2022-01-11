package com.example.loginsignupapp.repository

import com.example.loginsignupapp.network.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository() {

     suspend fun login(
         email: String,
         password: String
     ) = safeApiCall {
         api.login(email,password)
     }
}