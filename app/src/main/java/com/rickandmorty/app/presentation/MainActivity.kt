package com.rickandmorty.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rickandmorty.app.presentation.characters.CharactersListScreen
import com.rickandmorty.app.presentation.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                CharactersListScreen()
            }
        }
    }
}
