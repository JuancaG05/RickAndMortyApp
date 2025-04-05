package com.rickandmorty.app.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

object ApolloClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()

    val client = ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .okHttpClient(okHttpClient)
        .build()

}
