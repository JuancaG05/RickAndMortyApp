package com.rickandmorty.app.data.characters.repository

import com.rickandmorty.app.data.characters.datasources.ICharactersLocalDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender
import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class CharactersRepository(
    private val charactersLocalDataSource: ICharactersLocalDataSource,
    private val charactersRemoteDataSource: ICharactersRemoteDataSource,
) : ICharactersRepository {

    override suspend fun getCharacters(page: Int, filterByGender: CharacterGender?): Pair<List<Character?>, Int?> =
        charactersRemoteDataSource.getCharacters(page, filterByGender)

    override suspend fun getCharacter(id: String): Character? {
        val character = charactersRemoteDataSource.getCharacter(id)
        character?.let {
            charactersLocalDataSource.getFavouriteCharacters().find { it.id == id }?.let {
                character.isFavourite = true
            }
        }
        return character
    }


    override suspend fun upsertFavouriteCharacter(character: Character) {
        charactersLocalDataSource.upsertFavouriteCharacter(character)
    }

    override suspend fun deleteFavouriteCharacter(id: String) {
        charactersLocalDataSource.deleteFavouriteCharacter(id)
    }

    override suspend fun getFavouriteCharacters(): List<Character> =
        charactersLocalDataSource.getFavouriteCharacters()
}
