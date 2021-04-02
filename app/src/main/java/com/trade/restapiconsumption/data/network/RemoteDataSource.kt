package com.trade.restapiconsumption.data.network

import com.trade.restapiconsumption.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource
{
    companion object
    {
        private const val Base_URl = "http://secret-bastion-53803.herokuapp.com/api/"
    }

    fun<Api> buildApi(api: Class<Api>): Api
    {
        return Retrofit.Builder()
                .baseUrl(Base_URl)
                .client(
                        OkHttpClient.Builder().also { client ->
                            if (BuildConfig.DEBUG)
                            {
                                val logging =  HttpLoggingInterceptor()
                                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                                client.addInterceptor(logging)
                            }
                        }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
    }

}