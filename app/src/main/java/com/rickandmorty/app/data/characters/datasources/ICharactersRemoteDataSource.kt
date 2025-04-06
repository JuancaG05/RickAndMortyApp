package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender

interface ICharactersRemoteDataSource {
    suspend fun getCharacters(page: Int, filterByGender: CharacterGender?): Pair<List<Character?>, Int?>
}
