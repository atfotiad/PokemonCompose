package com.atfotiad.pokemoncompose.ui.pokemons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.ui.PokemonViewModel

@Composable
fun MySquadScreen(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel,
    onPokemonClick: (Pokemon) -> Unit = {}
) {
    val list = pokemonViewModel.getAllPokemonFromDatabase()
        .collectAsStateWithLifecycle(initialValue = listOf())

    Column(modifier) {
        SquadList(list = list.value) {
            onPokemonClick(it)
        }
    }
}


@Composable
fun SquadList(
    modifier: Modifier = Modifier,
    list: List<Pokemon>,
    onClick: (Pokemon) -> Unit
) {
    LazyColumn(
        modifier
    ) {
        items(count = list.size) { index ->
            val pokemon = list[index]
            PokemonItem(pokemon = pokemon, onPokemonClick = {
                onClick(pokemon)
            })
            Divider()
        }
    }
}