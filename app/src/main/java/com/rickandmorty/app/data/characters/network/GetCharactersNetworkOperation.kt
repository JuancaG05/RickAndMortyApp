package com.rickandmorty.app.data.characters.network

import com.apollographql.apollo3.api.Optional
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.ApolloClient

class GetCharactersNetworkOperation {

    suspend fun run(page: Int): GetCharactersPageQuery.Characters? {
        val response = ApolloClient.client.query(
            GetCharactersPageQuery(Optional.presentIfNotNull(page))
        ).execute()
        return response.data?.characters
    }
}
