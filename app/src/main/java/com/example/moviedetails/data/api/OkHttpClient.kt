package com.example.moviedetails.data.api

import okhttp3.OkHttpClient

interface OkhttpClient {
    companion object {
        private var INSTANCE : OkHttpClient?=null

        fun getClient():OkHttpClient {
                return INSTANCE ?: synchronized(this){
                    INSTANCE= OkHttpClient.Builder().addInterceptor(CustomHttpInterceptor()).build()
                    return INSTANCE as OkHttpClient
                }
        }
    }
}