package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class UpsertFavouriteCharacterUseCase(
    private val charactersRepository: ICharactersRepository,
) {

    suspend fun run(character: Character) {
        charactersRepository.upsertFavouriteCharacter(character)
    }
}
