package com.rickandmorty.app.data.characters.repository

import com.rickandmorty.app.data.characters.datasources.ICharactersLocalDataSource
import com.rickandmorty.app.data.characters.datasources.ICharactersRemoteDataSource
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.domain.characters.CharacterGender
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersRepositoryTest {

    private lateinit var charactersRepository: CharactersRepository
    private val charactersLocalDataSource = mockk<ICharactersLocalDataSource>(relaxUnitFun =  true)
    private val charactersRemoteDataSource = mockk<ICharactersRemoteDataSource>()

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
        charactersRepository = CharactersRepository(charactersLocalDataSource, charactersRemoteDataSource)
    }

    @Test
    fun `getCharacters returns a pair with a list of characters and next page`() = runTest {
        coEvery { charactersRemoteDataSource.getCharacters(1, null) } returns Pair(listOf(exampleCharacter), 2)

        val pairCharacterAndNextPage = charactersRepository.getCharacters(1, null)

        advanceUntilIdle()

        pairCharacterAndNextPage.first.forEach {
            assertNotNull(it)
        }
        assertEquals(1, pairCharacterAndNextPage.first.size)
        assertEquals(exampleCharacter, pairCharacterAndNextPage.first[0])
        assertEquals(2, pairCharacterAndNextPage.second)

        coVerify {
            charactersRemoteDataSource.getCharacters(1, null)
        }
    }

    @Test
    fun `getCharacter returns a character which is not favourite`() = runTest {
        coEvery { charactersRemoteDataSource.getCharacter("100") } returns exampleCharacter
        coEvery { charactersLocalDataSource.getFavouriteCharacters() } returns emptyList()

        val notFavouriteCharacter = charactersRepository.getCharacter("100")

        advanceUntilIdle()

        assertEquals(exampleCharacter, notFavouriteCharacter)

        coVerify {
            charactersRemoteDataSource.getCharacter("100")
            charactersLocalDataSource.getFavouriteCharacters()
        }
    }

    @Test
    fun `getCharacter returns a character which is favourite`() = runTest {
        coEvery { charactersRemoteDataSource.getCharacter("100") } returns exampleCharacter
        coEvery { charactersLocalDataSource.getFavouriteCharacters() } returns listOf(exampleCharacter)

        val favouriteCharacter = charactersRepository.getCharacter("100")

        advanceUntilIdle()

        assertEquals(exampleCharacter.copy(isFavourite = true), favouriteCharacter)

        coVerify {
            charactersRemoteDataSource.getCharacter("100")
            charactersLocalDataSource.getFavouriteCharacters()
        }
    }

    @Test
    fun `upsertFavouriteCharacter inserts character correctly`() = runTest {
        charactersRepository.upsertFavouriteCharacter(exampleCharacter)

        advanceUntilIdle()

        coVerify(exactly = 1) {
            charactersLocalDataSource.upsertFavouriteCharacter(exampleCharacter)
        }
    }

    @Test
    fun `deleteFavouriteCharacter removes character correctly`() = runTest {
        charactersRepository.deleteFavouriteCharacter(exampleCharacter.id!!)

        advanceUntilIdle()

        coVerify(exactly = 1) {
            charactersLocalDataSource.deleteFavouriteCharacter(exampleCharacter.id!!)
        }
    }

    @Test
    fun `getFavouriteCharacters returns a list of characters`() = runTest {
        coEvery { charactersLocalDataSource.getFavouriteCharacters() } returns listOf(exampleCharacter)

        val favouriteCharacters = charactersRepository.getFavouriteCharacters()

        advanceUntilIdle()

        favouriteCharacters.forEach {
            assertNotNull(it)
        }
        assertEquals(1, favouriteCharacters.size)
        assertEquals(exampleCharacter, favouriteCharacters[0])

        coVerify(exactly = 1) {
            charactersLocalDataSource.getFavouriteCharacters()
        }
    }
}
