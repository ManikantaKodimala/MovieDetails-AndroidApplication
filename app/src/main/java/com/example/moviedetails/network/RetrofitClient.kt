package com.example.moviedetails

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getClient(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().build()

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BASE_URL).build()
    }
}