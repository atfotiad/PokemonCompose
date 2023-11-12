package com.atfotiad.pokemoncompose.ui.pokemons

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.ui.PokemonViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel,
    pokemon: Pokemon
    ) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pokemon) {
        Log.i("pokemon", "SinglePokemon: ${pokemon.name}")
        Log.i("viewModel", "PokemonDetailsScreen: $viewModel")
    }

    ConstraintLayout(Modifier.verticalScroll(rememberScrollState(), true)) {
        val (img, fab) = createRefs()
        SinglePokemon(pokemon, modifier.constrainAs(img) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        })
        FloatingActionButton(modifier = Modifier.constrainAs(fab) {
            top.linkTo(img.bottom, margin = (-48).dp)
            end.linkTo(img.end, margin = 16.dp)
        },
            elevation = FloatingActionButtonDefaults.elevation(),
            shape = CircleShape,
            containerColor = Color.Red,
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.insertPokemon(pokemon)
                }
            }) {
            Icon(
                Icons.Filled.CatchingPokemon, contentDescription = "Floating action button"
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SinglePokemon(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Card(
        modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(30.dp),
    ) {
        Column(modifier.padding(16.dp)) {
            GlideImage(
                model = pokemon.sprites.other.officialArtwork.frontDefault,
                contentDescription = "pokemonSprite",
                modifier = modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onSurface, RectangleShape)
                    .padding(8.dp)
            )
            Text(text = pokemon.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = pokemon.pokeDexEntry.replace("[\n\t\u000c]".toRegex(), " "),
                fontWeight = FontWeight.Normal,
                modifier = modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(pokemon.types.size) { index ->
                    Text(text = "Type ${index.inc()}-> " + pokemon.types[index].type.name)
                }
            }
        }
    }
}
