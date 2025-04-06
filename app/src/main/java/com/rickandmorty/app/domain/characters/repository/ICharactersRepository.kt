package com.rickandmorty.app.domain.characters.repository

import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender

interface ICharactersRepository {
    suspend fun getCharacters(page: Int, filterByGender: CharacterGender?): Pair<List<Character?>, Int?>
    suspend fun upsertFavouriteCharacter(character: Character)
    suspend fun deleteFavouriteCharacter(id: String)
    suspend fun getFavouriteCharacters(): List<Character>
}
