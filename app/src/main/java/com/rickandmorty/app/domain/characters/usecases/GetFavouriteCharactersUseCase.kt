package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.domain.characters.repository.ICharactersRepository

class GetFavouriteCharactersUseCase(
    private val charactersRepository: ICharactersRepository,
) {

    suspend fun run() =
        charactersRepository.getFavouriteCharacters()
}
