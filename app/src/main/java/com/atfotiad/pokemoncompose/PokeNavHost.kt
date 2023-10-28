package com.atfotiad.pokemoncompose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.ui.PokemonViewModel
import com.atfotiad.pokemoncompose.ui.pokemons.PokemonDetailsScreen
import com.atfotiad.pokemoncompose.ui.pokemons.PokemonScreen

@Composable
fun PokeNavHost(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier,

    ) {
    NavHost(
        navController = navController,
        startDestination = PokemonList.route,
        modifier
    ) {
        composable(route = PokemonList.route) {
            PokemonScreen(pokemonViewModel = viewModel) { pokemon ->
                Log.i("poke", "PokemonScreen: passed $pokemon ")
                navController.navigateToSinglePokemon(pokemon)
            }
        }

        composable(
            route = SinglePokemonScreen.routeWithArgs, arguments = SinglePokemonScreen.arguments
        ) { navBackStackEntry ->
            val pokemonName = navBackStackEntry.arguments?.getString(SinglePokemonScreen.pokemonArg)

            LaunchedEffect("log") {
                Log.i("viewModel", "PokeNavHost: $viewModel ")
            }
            val pokemonFromViewModel =
                viewModel.pokemonList.collectAsLazyPagingItems().itemSnapshotList.items.find {
                    it.name == pokemonName
                }
            if (pokemonFromViewModel != null) {
                PokemonDetailsScreen(pokemon = pokemonFromViewModel)
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.navigateToSinglePokemon(pokemon: Pokemon) {
    this.navigateSingleTopTo("${SinglePokemonScreen.route}/${pokemon.name}")
}