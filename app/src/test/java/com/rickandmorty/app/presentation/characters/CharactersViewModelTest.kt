package com.rickandmorty.app.presentation.characters

import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender
import com.rickandmorty.app.domain.characters.usecases.DeleteFavouriteCharacterUseCase
import com.rickandmorty.app.domain.characters.usecases.GetCharacterUseCase
import com.rickandmorty.app.domain.characters.usecases.GetCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.GetFavouriteCharactersUseCase
import com.rickandmorty.app.domain.characters.usecases.UpsertFavouriteCharacterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest {

    private lateinit var charactersViewModel: CharactersViewModel
    private val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private val getCharacterUseCase = mockk<GetCharacterUseCase>()
    private val upsertFavouriteCharacterUseCase = mockk<UpsertFavouriteCharacterUseCase>()
    private val deleteFavouriteCharacterUseCase = mockk<DeleteFavouriteCharacterUseCase>()
    private val getFavouriteCharactersUseCase = mockk<GetFavouriteCharactersUseCase>()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val exampleCharacter = Character(
        id = "100",
        name = "Name",
        species = "Species",
        type = "Type",
        gender = CharacterGender.UNKNOWN,
        origin = "Origin",
        image = "https://url.to/image.png",
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        charactersViewModel = CharactersViewModel(
            getCharactersUseCase,
            getCharacterUseCase,
            upsertFavouriteCharacterUseCase,
            deleteFavouriteCharacterUseCase,
            getFavouriteCharactersUseCase
        )

        coEvery { getFavouriteCharactersUseCase.run() } returns emptyList()
        coEvery { getCharactersUseCase.run(1, null) } returns Pair(emptyList(), null)
    }

    @Test
    fun `getCharacter updates _currentCharacter with the character retrieved`() = testScope.runTest {
        coEvery { getCharacterUseCase.run("100") } returns exampleCharacter

        charactersViewModel.getCharacter("100")

        advanceUntilIdle()

        assertEquals(exampleCharacter, charactersViewModel.currentCharacter.first())
    }

    // Same with the other ViewModel methods!

}
