package com.rickandmorty.app.data.characters.datasources

import com.rickandmorty.app.data.characters.database.CharacterEntity
import com.rickandmorty.app.data.characters.database.CharactersDao
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
class CharactersLocalDataSourceTest {

    private lateinit var charactersLocalDataSource: CharactersLocalDataSource
    private val charactersDao = mockk<CharactersDao>(relaxUnitFun =  true)

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

    private val exampleCharacterEntity = CharacterEntity(
        id = "100",
        name = "Name",
        species = "Species",
        type = "Type",
        gender = "UNKNOWN",
        origin = "Origin",
        image = "https://url.to/image.png",
    )

    @Before
    fun setUp() {
        charactersLocalDataSource = CharactersLocalDataSource(charactersDao)
    }

    @Test
    fun `upsertFavouriteCharacter inserts character correctly`() = runTest {
        charactersLocalDataSource.upsertFavouriteCharacter(exampleCharacter)

        advanceUntilIdle()

        coVerify(exactly = 1) {
            charactersDao.upsertCharacter(exampleCharacterEntity)
        }
    }

    @Test
    fun `deleteFavouriteCharacter removes character correctly`() = runTest {
        charactersLocalDataSource.deleteFavouriteCharacter(exampleCharacter.id!!)

        advanceUntilIdle()

        coVerify(exactly = 1) {
            charactersDao.deleteCharacter(exampleCharacter.id!!)
        }
    }

    @Test
    fun `getFavouriteCharacters returns a list of characters`() = runTest {
        coEvery { charactersDao.getAllCharacters() } returns listOf(exampleCharacterEntity)

        val favouriteCharacters = charactersLocalDataSource.getFavouriteCharacters()

        advanceUntilIdle()

        favouriteCharacters.forEach {
            assertNotNull(it)
        }
        assertEquals(1, favouriteCharacters.size)
        assertEquals(exampleCharacter, favouriteCharacters[0])

        coVerify(exactly = 1) {
            charactersDao.getAllCharacters()
        }
    }
}
