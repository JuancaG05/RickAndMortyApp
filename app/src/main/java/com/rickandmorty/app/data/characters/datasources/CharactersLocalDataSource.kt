package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.data.characters.database.CharacterEntity
import com.rickandmorty.app.data.characters.database.CharactersDao
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender

class CharactersLocalDataSource(
     private val charactersDao: CharactersDao
) : ICharactersLocalDataSource {
    override suspend fun upsertFavouriteCharacter(character: Character) {
        charactersDao.upsertCharacter(character.toEntity())
    }

    override suspend fun deleteFavouriteCharacter(id: String) {
        charactersDao.deleteCharacter(id)
    }

    override suspend fun getFavouriteCharacters(): List<Character> =
        charactersDao.getAllCharacters().map { it.toModel() }


    private fun CharacterEntity.toModel() =
        Character(
            id = id,
            name = name,
            species = species,
            type = type,
            gender = gender?.let { CharacterGender.valueOf(it) },
            origin = origin,
            image = image,
            isFavourite = true,
        )

    private fun Character.toEntity() =
        CharacterEntity(
            id = id!!,
            name = name,
            species = species,
            type = type,
            gender = gender?.name,
            origin = origin,
            image = image,
        )
}
