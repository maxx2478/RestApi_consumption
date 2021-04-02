package com.trade.restapiconsumption.data.repository

import com.trade.restapiconsumption.data.UserPreferences
import com.trade.restapiconsumption.data.network.AuthApi

class AuthRepository (private val api: AuthApi,
  private val preferences: UserPreferences) : BaseRepository()
{

    suspend fun login( email: String, password:String)
    =  SafeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token:String)
    {
        preferences.saveAuthToken(token)
    }


}