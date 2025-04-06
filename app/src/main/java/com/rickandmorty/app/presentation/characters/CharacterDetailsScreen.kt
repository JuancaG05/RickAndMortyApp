package com.rickandmorty.app.presentation.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rickandmorty.app.R
import com.rickandmorty.app.domain.characters.Character
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    character: Character,
    onClickBack: () -> Unit,
    viewModel: CharactersViewModel = getViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Character details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val painter = rememberAsyncImagePainter(
                        model = character.image,
                        placeholder = painterResource(R.drawable.default_character_image),
                        error = painterResource(R.drawable.default_character_image)
                    )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    character.name?.let {
                        Text(
                            text = it,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    character.origin?.let {
                        Text(
                            text = "Origin: $it",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    character.gender?.let {
                        Text(
                            text = "Gender: ${it.name}",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    character.species?.let {
                        Text(
                            text = "Species: $it",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    character.type?.let {
                        Text(
                            text = "Type: $it",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    IconButton(
                        onClick = {  },
                        modifier = Modifier.size(48.dp)
                    ) {
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
        }
    }

}
