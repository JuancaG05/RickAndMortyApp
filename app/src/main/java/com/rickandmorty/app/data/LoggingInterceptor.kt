package com.rickandmorty.app.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("ApolloRequest", "Request URL: ${request.url}")
        Log.d("ApolloRequest", "Request Headers: ${request.headers}")

        val response = chain.proceed(request)

        Log.d("ApolloResponse", "Response Status: ${response.code}")
        val responseBody = response.body
        if (responseBody != null) {
            val responseBodyString = responseBody.string()
            Log.d("ApolloResponse", "Response Body: $responseBodyString")

            val newResponseBody = responseBodyString.toByteArray().toResponseBody(responseBody.contentType())
            return response.newBuilder().body(newResponseBody).build()
        }

        return response
    }
}
