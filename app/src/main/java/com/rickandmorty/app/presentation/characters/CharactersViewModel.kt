package com.rickandmorty.app.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _listOfCharacters: MutableStateFlow<Pair<List<Character?>, Int?>> = MutableStateFlow(Pair(emptyList(), null))
    val listOfCharacters: StateFlow<Pair<List<Character?>, Int?>> = _listOfCharacters

    init {
        getCharacters(page = 1)
    }

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            val result = getCharactersUseCase.run(page)
            val accumulatedCharacters = _listOfCharacters.value.first + result.first
            _listOfCharacters.update { Pair(accumulatedCharacters, result.second) }
        }
    }
}
