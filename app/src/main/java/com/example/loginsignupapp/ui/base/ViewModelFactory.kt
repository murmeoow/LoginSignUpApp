package com.example.loginsignupapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginsignupapp.data.repository.AuthRepository
import com.example.loginsignupapp.data.repository.BaseRepository
import com.example.loginsignupapp.data.repository.UserRepository
import com.example.loginsignupapp.ui.auth.AuthViewModel
import com.example.loginsignupapp.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }


}