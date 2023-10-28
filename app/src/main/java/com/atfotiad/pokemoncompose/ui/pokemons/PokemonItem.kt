package com.atfotiad.pokemoncompose.ui.pokemons

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.atfotiad.pokemoncompose.model.Pokemon
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onPokemonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon) },
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                model = pokemon.sprites.other.officialArtwork.frontDefault,
                contentDescription = "pokemonSprite",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                    .padding(8.dp)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = pokemon.name,
                textAlign = TextAlign.Center
            )
            Icon(Icons.Filled.ArrowForward, contentDescription = "Click")
        }
    }
}