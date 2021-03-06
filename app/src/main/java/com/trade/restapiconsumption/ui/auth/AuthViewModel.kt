package com.trade.restapiconsumption.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trade.restapiconsumption.data.network.Resource
import com.trade.restapiconsumption.data.repository.AuthRepository
import com.trade.restapiconsumption.data.responses.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(
        private val repository: AuthRepository
) : ViewModel()
{

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

   fun login(
           email:String,
           password:String)
  = viewModelScope.launch {
       _loginResponse.value = Resource.Loading
       _loginResponse.value = repository.login(email, password)
   }

    suspend fun saveAuthToken(token:String) =
        repository.saveAuthToken(token)

}