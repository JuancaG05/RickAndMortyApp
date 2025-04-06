package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.GetCharacterQuery
import com.rickandmorty.app.GetCharactersPageQuery
import com.rickandmorty.app.data.characters.network.GetCharacterNetworkOperation
import com.rickandmorty.app.data.characters.network.GetCharactersNetworkOperation
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender

class CharactersRemoteDataSource : ICharactersRemoteDataSource {

    override suspend fun getCharacters(page: Int, filterByGender: CharacterGender?): Pair<List<Character?>, Int?> {
        val charactersResponse = GetCharactersNetworkOperation().run(page, filterByGender)
        val nextPage = charactersResponse?.info?.next
        val listOfCharacters = charactersResponse?.results?.let { results ->
            results.map { it?.toModel() }
        } ?: emptyList()
        return Pair(listOfCharacters, nextPage)
    }

    override suspend fun getCharacter(id: String): Character? {
        val characterResponse = GetCharacterNetworkOperation().run(id)
        return characterResponse?.toModel()
    }

    private fun GetCharactersPageQuery.Result.toModel() =
        Character(
            id = id,
            name = name,
            species = species,
            type = type,
            gender = gender?.let { CharacterGender.fromString(it) },
            origin = origin?.name,
            image = image,
        )

    private fun GetCharacterQuery.Character.toModel() =
        Character(
            id = id,
            name = name,
            species = species,
            type = type,
            gender = gender?.let { CharacterGender.fromString(it) },
            origin = origin?.name,
            image = image,
        )
}
