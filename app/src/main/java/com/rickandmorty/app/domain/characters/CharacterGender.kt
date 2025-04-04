package com.rickandmorty.app.domain.characters

enum class CharacterGender {
    FEMALE, MALE, GENDERLESS, UNKNOWN;

    companion object {
        fun fromString(genderString: String): CharacterGender =
            when (genderString) {
                "Female" -> FEMALE
                "Male" -> MALE
                "Genderless" -> GENDERLESS
                "unknown" -> UNKNOWN
                else -> UNKNOWN
            }
    }
}
