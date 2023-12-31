package com.atfotiad.pokemoncompose.ui.pokemons

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.ui.PokemonViewModel

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel,
    onPokemonClick: (Pokemon) -> Unit = {}
) {
    Column(modifier) {
        val list = pokemonViewModel.pokemonList.collectAsLazyPagingItems()
        PokemonList(
            list = list
        ) { pokemon: Pokemon ->
            run {
                Log.i(
                    "Poke",
                    "PokemonItem: Clicked ${pokemon.name}"
                )
                onPokemonClick(pokemon)
            }
        }
    }
}