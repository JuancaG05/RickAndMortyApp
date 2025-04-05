package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.domain.characters.Character

interface ICharactersRemoteDataSource {
    suspend fun getCharacters(page: Int): Pair<List<Character?>, Int?>
}
