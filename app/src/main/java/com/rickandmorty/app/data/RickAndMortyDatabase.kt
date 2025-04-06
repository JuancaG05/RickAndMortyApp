package com.rickandmorty.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rickandmorty.app.data.characters.database.CharacterEntity
import com.rickandmorty.app.data.characters.database.CharactersDao

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    companion object {
        @Volatile
        private var INSTANCE: RickAndMortyDatabase? = null

        fun getDatabase(context: Context): RickAndMortyDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    RickAndMortyDatabase::class.java,
                    "rick_and_morty_database"
                ).build()
                    .also { INSTANCE = it }
            }
    }
}
