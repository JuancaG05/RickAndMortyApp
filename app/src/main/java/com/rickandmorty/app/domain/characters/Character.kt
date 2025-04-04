package com.rickandmorty.app.domain.characters

data class Character(
    val id: String,
    val name: String,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: CharacterLocation,
    val image: String,
    val isFavourite: Boolean = false,
)
