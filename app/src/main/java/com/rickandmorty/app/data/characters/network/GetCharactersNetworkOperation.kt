package com.rickandmorty.app.data.characters.network

import com.apollographql.apollo3.api.Optional
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.ApolloClient

class GetCharactersNetworkOperation(
    private val page: Int
) {

    suspend fun run(): GetCharactersPageQuery.Characters? {
        val response = ApolloClient.client.query(
            GetCharactersPageQuery(Optional.presentIfNotNull(page))
        ).execute()
        return response.data?.characters
    }
}
