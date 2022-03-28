package com.example.moviedetails.network

import com.example.moviedetails.API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CustomHttpInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
        request=request.newBuilder().url(url).build()
        val response = chain.proceed(request)
        if(!response.isSuccessful) {
            ErrorInterceptor(response).handleResponse()
        }
        return response

    }
}


