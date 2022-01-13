package com.example.loginsignupapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.loginsignupapp.R
import com.example.loginsignupapp.data.network.Resource
import com.example.loginsignupapp.data.network.UserApi
import com.example.loginsignupapp.data.repository.UserRepository
import com.example.loginsignupapp.data.responses.User
import com.example.loginsignupapp.databinding.FragmentHomeBinding
import com.example.loginsignupapp.ui.base.BaseFragment
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    updateUI(it.value.user)
                }

            }
        })
    }

    private fun updateUI(user: User){
        with(binding){
            tvId.text = user.id.toString()
            tvName.text = user.name
            tvEmail.text = user.email
        }
    }

    override fun getViewModel()= HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first()}
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}