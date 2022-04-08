package com.example.moviedetails.data.api

import com.example.moviedetails.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CustomHttpInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}


