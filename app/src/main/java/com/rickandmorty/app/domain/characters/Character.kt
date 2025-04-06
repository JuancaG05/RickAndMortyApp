package com.rickandmorty.app.domain.characters

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String?,
    val name: String?,
    val species: String?,
    val type: String?,
    val gender: CharacterGender?,
    val origin: String?,
    val image: String?,
    var isFavourite: Boolean = false,
)
