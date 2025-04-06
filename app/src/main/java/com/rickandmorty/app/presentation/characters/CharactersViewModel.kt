package com.rickandmorty.app.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender
import com.rickandmorty.app.domain.characters.usecases.DeleteFavouriteCharacterUseCase
import com.rickandmorty.app.domain.characters.usecases.GetCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.GetFavouriteCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.UpsertFavouriteCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val upsertFavouriteCharacterUseCase: UpsertFavouriteCharacterUseCase,
    private val deleteFavouriteCharacterUseCase: DeleteFavouriteCharacterUseCase,
    private val getFavouriteCharactersUseCase: GetFavouriteCharactersUseCase,
) : ViewModel() {

    private val _listOfCharacters: MutableStateFlow<Pair<List<Character?>, Int?>> = MutableStateFlow(Pair(emptyList(), null))
    val listOfCharacters: StateFlow<Pair<List<Character?>, Int?>> = _listOfCharacters

    private val _listOfFavouriteCharacters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())

    init {
        runBlocking {
            getFavouriteCharacters()
        }
        getCharacters(page = 1, filterByGender = null)
    }

    fun getCharacters(page: Int, filterByGender: CharacterGender?) {
        viewModelScope.launch {
            val result = getCharactersUseCase.run(page, filterByGender)
            result.first.forEach { characterRetrieved ->
                _listOfFavouriteCharacters.value.forEach { favouriteCharacter ->
                    if (characterRetrieved?.id == favouriteCharacter.id) {
                        characterRetrieved?.isFavourite = true
                    }
                }
            }
            if (page == 1) {
                _listOfCharacters.update { result }
            } else {
                val accumulatedCharacters = _listOfCharacters.value.first + result.first
                _listOfCharacters.update { Pair(accumulatedCharacters, result.second) }
            }
        }
    }

    fun upsertFavouriteCharacter(character: Character) {
        viewModelScope.launch {
            upsertFavouriteCharacterUseCase.run(character)
        }
    }

    fun deleteFavouriteCharacter(id: String) {
        viewModelScope.launch {
            deleteFavouriteCharacterUseCase.run(id)
        }
    }

    private fun getFavouriteCharacters() {
        viewModelScope.launch {
            val result = getFavouriteCharactersUseCase.run()
            _listOfFavouriteCharacters.update { result }
        }
    }
}
