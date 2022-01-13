package com.example.loginsignupapp.data.repository

import com.example.loginsignupapp.data.UserPreferences
import com.example.loginsignupapp.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
): BaseRepository() {

     suspend fun login(
         email: String,
         password: String
     ) = safeApiCall {
         api.login(email,password)
     }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}