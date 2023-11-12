package com.atfotiad.pokemoncompose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.ui.PokemonViewModel
import com.atfotiad.pokemoncompose.ui.pokemons.CameraScreen
import com.atfotiad.pokemoncompose.ui.pokemons.MySquadScreen
import com.atfotiad.pokemoncompose.ui.pokemons.PokemonDetailsScreen
import com.atfotiad.pokemoncompose.ui.pokemons.PokemonScreen
import com.atfotiad.pokemoncompose.ui.pokemons.WhoisThatPokemon

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
                } ?: viewModel.getAllPokemonFromDatabase()
                    .collectAsStateWithLifecycle(initialValue = listOf()).value.find { it.name == pokemonName }
            Log.i("SinglePoke", "PokeNavHost: selected pokemon is: $pokemonFromViewModel")
            if (pokemonFromViewModel != null) {
                PokemonDetailsScreen(viewModel = viewModel, pokemon = pokemonFromViewModel)
            }
        }
        composable(route = RequestFromContentUriScreen.route) {
            WhoisThatPokemon {
                navController.navigateSingleTopTo(CameraScreen.route)
            }
        }

        composable(route = CameraScreen.route) {
            CameraScreen()
        }

        composable(route = MySquadScreen.route) {
            MySquadScreen(pokemonViewModel = viewModel) { pokemon ->
                Log.i("toSingle", "PokeNavHost: from DB selectedPokemon is: $pokemon")
                navController.navigateToSinglePokemon(pokemon)
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = false
    }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.navigateToSinglePokemon(pokemon: Pokemon) {
    Log.i("toSingle", "navigateToSinglePokemon with Pokemon: $pokemon")
    this.navigateSingleTopTo("${SinglePokemonScreen.route}/${pokemon.name}")
}