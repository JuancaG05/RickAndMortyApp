package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.domain.characters.Character

interface ICharactersLocalDataSource {
    suspend fun upsertFavouriteCharacter(character: Character)
    suspend fun deleteFavouriteCharacter(id: String)
    suspend fun getFavouriteCharacters(): List<Character>
}
