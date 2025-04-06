package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class DeleteFavouriteCharacterUseCase(
    private val charactersRepository: ICharactersRepository,
) {

    suspend fun run(id: String) {
        charactersRepository.deleteFavouriteCharacter(id)
    }
}
