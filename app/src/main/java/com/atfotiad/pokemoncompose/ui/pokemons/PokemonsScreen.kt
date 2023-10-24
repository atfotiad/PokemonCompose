package com.atfotiad.pokemoncompose.ui.pokemons

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.atfotiad.pokemoncompose.ui.PokemonViewModel

@Composable
fun PokemonScreen(
    modifier: Modifier = Modifier,
    pokemonViewModel: PokemonViewModel = hiltViewModel()
) {
    Column(modifier) {
        PokemonList(
            list = pokemonViewModel.pokemonList.collectAsLazyPagingItems(),
            onPokemonClick = {}
        )
    }
}