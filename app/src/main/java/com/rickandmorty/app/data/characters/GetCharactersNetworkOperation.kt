package com.rickandmorty.app.data.characters

import com.apollographql.apollo3.api.Optional
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.ApolloClient

class GetCharactersNetworkOperation(
    private val page: Int
) {

    suspend fun run(apolloClient: ApolloClient): GetCharactersPageQuery.Characters? {
        val response = apolloClient.client.query(
            GetCharactersPageQuery(Optional.presentIfNotNull(page))
        ).execute()
        return response.data?.characters
    }
}
