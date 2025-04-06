package com.rickandmorty.app.data.characters.network

import com.apollographql.apollo3.api.Optional
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.ApolloClient
import com.rickandmorty.app.domain.characters.CharacterGender

class GetCharactersNetworkOperation {

    suspend fun run(page: Int, filterByGender: CharacterGender?): GetCharactersPageQuery.Characters? {
        val response = ApolloClient.client.query(
            GetCharactersPageQuery(Optional.presentIfNotNull(page), Optional.presentIfNotNull(filterByGender?.name))
        ).execute()
        return response.data?.characters
    }
}
