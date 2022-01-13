package com.example.loginsignupapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.loginsignupapp.databinding.FragmentLoginBinding
import com.example.loginsignupapp.data.network.AuthApi
import com.example.loginsignupapp.data.network.Resource
import com.example.loginsignupapp.data.repository.AuthRepository
import com.example.loginsignupapp.ui.base.BaseFragment
import com.example.loginsignupapp.ui.enable
import com.example.loginsignupapp.ui.handleApiError
import com.example.loginsignupapp.ui.home.HomeActivity
import com.example.loginsignupapp.ui.startNewActivity
import com.example.loginsignupapp.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.progressBar.visible(it is Resource.Lodaing)

            when(it){
                is Resource.Success ->{
                        lifecycleScope.launch {
                            viewModel.saveAuthToken(it.value.user.access_token!!)
                            requireActivity().startNewActivity(HomeActivity::class.java)
                        }
                }
                is Resource.Failure-> handleApiError(it)
            }
        })

        binding.etPassword.addTextChangedListener{
            val email = binding.etEmail.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            binding.progressBar.visible(true)
            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false )

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}