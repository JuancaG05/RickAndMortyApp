package com.rickandmorty.app.domain.characters.repository

import com.rickandmorty.app.domain.characters.Character

interface ICharactersRepository {
    suspend fun getCharacters(page: Int): Pair<List<Character?>, Int?>
}
