package com.trade.restapiconsumption.ui.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.trade.restapiconsumption.databinding.FragmentLoginBinding
import com.trade.restapiconsumption.data.network.AuthApi
import com.trade.restapiconsumption.data.network.Resource
import com.trade.restapiconsumption.data.repository.AuthRepository
import com.trade.restapiconsumption.ui.auth.base.BaseFragment
import com.trade.restapiconsumption.ui.auth.home.HomeActivity
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel,
        FragmentLoginBinding,
        AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.login.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {

                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.values.user.access_token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }

                }

                is Resource.Failure -> {
                    handleApiError(it)
                    //Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }


            }
        })

        binding.password.addTextChangedListener {
            val emailx = binding.email.text.toString().trim()
            binding.login.enable(emailx.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (email.length>0 && password.length>0)
            {

                viewModel.login(email, password)
            }

        }

    }

    override fun getviewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?
    )  = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}