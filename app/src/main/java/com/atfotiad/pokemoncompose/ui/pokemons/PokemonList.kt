package com.atfotiad.pokemoncompose.ui.pokemons


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.atfotiad.pokemoncompose.model.Pokemon

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<Pokemon>,
    onPokemonClick: () -> Unit
) {
    LazyColumn(modifier
        ) {
        items(count = list.itemCount) {index->
            val pokemon = list[index]!!
            PokemonItem(pokemonName = pokemon.name, onPokemonClick = { onPokemonClick() })
            Divider()
        }
    }
}