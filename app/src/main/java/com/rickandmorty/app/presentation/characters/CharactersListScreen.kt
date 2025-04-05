package com.rickandmorty.app.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rickandmorty.app.R
import com.rickandmorty.app.domain.characters.Character
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersListScreen(
    viewModel: CharactersViewModel = getViewModel()
) {
    val listOfCharacters by viewModel.listOfCharacters.collectAsState()
    val characters = listOfCharacters.first
    val nextPage = listOfCharacters.second

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characters) { character ->
            character?.let {
                CharacterCard(
                    character = character,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun CharacterCard(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(
                model = character.image,
                placeholder = painterResource(R.drawable.default_character_image),
                error = painterResource(R.drawable.default_character_image)
            )
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                character.name?.let {
                    Text(
                        text = "Name: $it"
                    )
                }
                character.origin?.let {
                    Text(
                        text = "Origin: $it"
                    )
                }
                character.gender?.let {
                    Text(
                        text = "Gender: ${it.name}"
                    )
                }
            }

            Icon(
                painter = painterResource(id =
                    if (character.isFavourite) R.drawable.favourite
                    else R.drawable.not_favourite
                ),
                contentDescription = null
            )
        }
    }
}
