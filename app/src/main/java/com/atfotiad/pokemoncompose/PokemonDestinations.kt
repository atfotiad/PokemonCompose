package com.atfotiad.pokemoncompose

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface PokemonDestination {
    val route: String
}

object PokemonList : PokemonDestination {
    override val route: String = "pokemonList"
}

object SinglePokemonScreen : PokemonDestination {
    override val route: String = "singlePokemon"
    const val pokemonArg = "pokemonName"
    val routeWithArgs = "${route}/{${pokemonArg}}"
    val arguments = listOf(
        navArgument(pokemonArg) { type = NavType.StringType }
    )
}