package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class GetCharacterUseCase(
    private val charactersRepository: ICharactersRepository,
) {

    suspend fun run(id: String) =
        charactersRepository.getCharacter(id)
}
