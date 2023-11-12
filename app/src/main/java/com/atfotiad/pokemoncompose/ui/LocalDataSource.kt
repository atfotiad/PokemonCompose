package com.atfotiad.pokemoncompose.ui

import com.atfotiad.pokemoncompose.db.PokemonDao
import com.atfotiad.pokemoncompose.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    suspend fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun getPokemon(name: String): Pokemon? {
       return pokemonDao.getPokemonByName(name)
    }

    fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonDao.getAllPokemon()
    }
}