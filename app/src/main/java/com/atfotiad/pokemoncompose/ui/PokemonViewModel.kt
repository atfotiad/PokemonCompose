package com.atfotiad.pokemoncompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.atfotiad.pokemoncompose.model.OfficialArtwork
import com.atfotiad.pokemoncompose.model.Other
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.model.Sprites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {

    var pokemon: Pokemon? by mutableStateOf(
        Pokemon(
            "",
            Sprites(Other(OfficialArtwork(""))),
            arrayListOf(),
            ""
        )
    )
    val pokemonList = repository.getPokeResults().cachedIn(viewModelScope)

    fun insertPokemon(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.insertPokemon(pokemon)
        }
    }

    fun getAllPokemonFromDatabase(): Flow<List<Pokemon>> {
        return repository.localDataSource.getAllPokemon()
    }
}