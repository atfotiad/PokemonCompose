package com.atfotiad.pokemoncompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    repository: PokeRepository
) : ViewModel() {

    val pokemonList =
        repository.getPokeResults().cachedIn(viewModelScope)

}