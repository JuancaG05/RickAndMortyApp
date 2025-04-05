package com.rickandmorty.app.data.characters.repository

import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class CharactersRepository(
    private val charactersRemoteDataSource: ICharactersRemoteDataSource,
) : ICharactersRepository {

    override suspend fun getCharacters(page: Int): Pair<List<Character?>, Int?> =
        charactersRemoteDataSource.getCharacters(page)
}
