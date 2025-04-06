package com.rickandmorty.app.data.characters.network

import android.util.Log
import com.rickandmorty.app.GetCharacterQuery
import com.rickandmorty.app.data.ApolloClientProvider

class GetCharacterNetworkOperation {

    suspend fun run(id: String): GetCharacterQuery.Character? {
        try {
            val response = ApolloClientProvider.client.query(
                GetCharacterQuery(id)
            ).execute()
            return response.data?.character
        } catch (e: Exception) {
            Log.e("GetCharacterNetworkOperation", "Network operation to retrieve character could not be executed: ${e.message.toString()}")
            return null
        }
    }
}
