package com.rickandmorty.app.domain.characters.repository

import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender

interface ICharactersRepository {
    suspend fun getCharacters(page: Int, filterByGender: CharacterGender?): Pair<List<Character?>, Int?>
}
