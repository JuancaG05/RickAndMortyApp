package com.rickandmorty.app.domain.characters.usecases

import com.rickandmorty.app.data.characters.repository.CharactersRepository
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCharacterUseCaseTest {

    private lateinit var getCharacterUseCase: GetCharacterUseCase
    private val charactersRepository = mockk<CharactersRepository>(relaxUnitFun =  true)

    private val exampleCharacter = Character(
        id = "100",
        name = "Name",
        species = "Species",
        type = "Type",
        gender = CharacterGender.UNKNOWN,
        origin = "Origin",
        image = "https://url.to/image.png",
        isFavourite = true
    )

    @Before
    fun setUp() {
        getCharacterUseCase = GetCharacterUseCase(charactersRepository)
    }

    @Test
    fun `run use case returns a character`() = runTest {
        coEvery { charactersRepository.getCharacter("100") } returns exampleCharacter

        val character = getCharacterUseCase.run("100")

        advanceUntilIdle()

        assertEquals(exampleCharacter, character)

        coVerify(exactly = 1) {
            charactersRepository.getCharacter("100")
        }
    }
}
