package com.example.moviedetails.data.api

import com.example.moviedetails.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitClient {

    companion object {
        private val INSTANCE : Retrofit?=null

        fun getClient(): Retrofit {
            val okHttpClient = OkHttpClient.Builder().addInterceptor(CustomHttpInterceptor()).build()
            return INSTANCE ?: synchronized(this){
                return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL).build()
            }
        }
    }

}