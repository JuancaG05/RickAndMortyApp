package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class GetCharactersUseCase(
    private val charactersRepository: ICharactersRepository,
) {

    suspend fun run(page: Int) =
        charactersRepository.getCharacters(page)
}
