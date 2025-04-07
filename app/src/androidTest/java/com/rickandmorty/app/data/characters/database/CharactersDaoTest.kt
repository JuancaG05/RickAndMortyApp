package com.rickandmorty.app.data.characters.database

import androidx.test.platform.app.InstrumentationRegistry
import com.rickandmorty.app.data.RickAndMortyDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CharactersDaoTest {

    private lateinit var charactersDao: CharactersDao

    private val exampleCharacterEntity = CharacterEntity(
        id = "100",
        name = "Name",
        species = "Species",
        type = "Type",
        gender = "unknown",
        origin = "Origin",
        image = "https://url.to/image.png",
    )

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        RickAndMortyDatabase.switchToInMemory(context)
        charactersDao = RickAndMortyDatabase.getDatabase(context).charactersDao()
    }

    @Test
    fun testUpsertCharacter() = runTest {
        charactersDao.upsertCharacter(exampleCharacterEntity)
        val charactersInserted = charactersDao.getAllCharacters()

        charactersInserted.forEach {
            assertNotNull(it)
        }
        assertEquals(1, charactersInserted.size)
        assertEquals(exampleCharacterEntity, charactersInserted[0])

    }

    @Test
    fun testDeleteCharacter() = runTest {
        charactersDao.upsertCharacter(exampleCharacterEntity)

        charactersDao.deleteCharacter(exampleCharacterEntity.id)
        val charactersInserted = charactersDao.getAllCharacters()

        assertEquals(0, charactersInserted.size)
    }

    @Test
    fun testGetAllCharacters() = runTest {
        charactersDao.upsertCharacter(exampleCharacterEntity)
        charactersDao.upsertCharacter(exampleCharacterEntity.copy(id = "200"))

        val charactersInserted = charactersDao.getAllCharacters()

        charactersInserted.forEach {
            assertNotNull(it)
        }
        assertEquals(2, charactersInserted.size)
        assertEquals(exampleCharacterEntity, charactersInserted[0])
        assertEquals(exampleCharacterEntity.copy(id = "200"), charactersInserted[1])
    }

}
