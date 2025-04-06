package com.rickandmorty.app.data.characters.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: String?,
    val image: String?,
)
