package com.atfotiad.pokemoncompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface PokemonDestination {
    val icon: ImageVector
    val route: String
}

object PokemonList : PokemonDestination {
    override val icon: ImageVector = Icons.Filled.List
    override val route: String = "pokemonList"
}

object SinglePokemonScreen : PokemonDestination {
    override val icon: ImageVector = Icons.Filled.Person
    override val route: String = "singlePokemon"
    const val pokemonArg = "pokemonName"
    val routeWithArgs = "${route}/{${pokemonArg}}"
    val arguments = listOf(navArgument(pokemonArg) { type = NavType.StringType })
}

object RequestFromContentUriScreen : PokemonDestination {
    override val icon: ImageVector = Icons.Filled.Search
    override val route: String = "Upload"
}

object CameraScreen: PokemonDestination {
    override val icon: ImageVector = Icons.Filled.CameraAlt
    override val route: String = "Camera"
}

object MySquadScreen: PokemonDestination {
    override val icon: ImageVector = Icons.Filled.Group
    override val route: String = "SquadList"
}

val pokemonTabRowScreens = listOf(PokemonList, RequestFromContentUriScreen, MySquadScreen)