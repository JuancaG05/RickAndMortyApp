package com.rickandmorty.app.data.characters.network

import android.util.Log
import com.apollographql.apollo3.api.Optional
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.ApolloClientProvider
import com.rickandmorty.app.domain.characters.CharacterGender

class GetCharactersNetworkOperation {

    suspend fun run(page: Int, filterByGender: CharacterGender?): GetCharactersPageQuery.Characters? {
        try {
            val response = ApolloClientProvider.client.query(
                GetCharactersPageQuery(
                    Optional.presentIfNotNull(page),
                    Optional.presentIfNotNull(filterByGender?.name)
                )
            ).execute()
            return response.data?.characters
        }
        catch (e: Exception) {
            Log.e("GetCharactersNetworkOperation","Network operation to retrieve characters could not be executed: ${e.message.toString()}")
            return null
        }

    }
}
