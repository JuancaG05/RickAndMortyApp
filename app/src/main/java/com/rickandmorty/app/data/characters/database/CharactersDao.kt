package com.rickandmorty.app.data.characters.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharactersDao {

    @Upsert
    suspend fun upsertCharacter(character: CharacterEntity)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacter(id: String)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>
}
