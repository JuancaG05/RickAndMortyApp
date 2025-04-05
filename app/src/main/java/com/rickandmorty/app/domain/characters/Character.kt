package com.rickandmorty.app.domain.characters

data class Character(
    val id: String?,
    val name: String?,
    val species: String?,
    val type: String?,
    val gender: CharacterGender?,
    val origin: String?,
    val image: String?,
    val isFavourite: Boolean = false,
)
