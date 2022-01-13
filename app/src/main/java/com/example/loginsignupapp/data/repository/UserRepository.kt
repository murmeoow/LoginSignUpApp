package com.example.loginsignupapp.data.repository

import com.example.loginsignupapp.data.network.UserApi

class UserRepository(
    private val api: UserApi,
): BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}