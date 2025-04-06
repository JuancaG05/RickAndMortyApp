package com.rickandmorty.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rickandmorty.app.domain.characters.Character
import com.rickandmorty.app.presentation.characters.CharacterDetailsScreen
import com.rickandmorty.app.presentation.characters.CharactersListScreen
import com.rickandmorty.app.presentation.theme.RickAndMortyAppTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CharactersList
    ) {
        composable<CharactersList> {
            CharactersListScreen(
                onClickOnCharacterCard = { character ->
                    val characterJson = Json.encodeToString(Character.serializer(), character)
                    val encodedCharacterJson = URLEncoder.encode(characterJson, StandardCharsets.UTF_8.toString())
                    navController.navigate(
                        route = "characterDetails/${encodedCharacterJson}"
                    )
                }
            )
        }
        composable(
            route = "characterDetails/{characterJson}",
            arguments = listOf(navArgument("characterJson") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val encodedCharacterJson = navBackStackEntry.arguments?.getString("characterJson")
            val characterJson = encodedCharacterJson?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            }
            val character = characterJson?.let { Json.decodeFromString<Character>(it) }
            character?.let {
                CharacterDetailsScreen(character = it, onClickBack = {
                    navController.navigate(CharactersList)
                })
            }
        }
    }
}

@Serializable
object CharactersList
