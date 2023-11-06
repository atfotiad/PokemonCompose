package com.atfotiad.pokemoncompose

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.atfotiad.pokemoncompose.ui.PokemonViewModel
import com.atfotiad.pokemoncompose.ui.components.PokemonTabRow
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokemonComposeApplication : Application() {

    companion object {

        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun PokeApp() {
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val viewModel: PokemonViewModel = hiltViewModel()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    pokemonTabRowScreens.find { it.route == currentDestination?.route }
                        ?: SinglePokemonScreen
                Scaffold(bottomBar = {
                    PokemonTabRow(
                        allScreens = pokemonTabRowScreens,
                        onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                        currentScreen = currentScreen
                    )
                }) { innerPadding ->
                    PokeNavHost(
                        navController = navController, viewModel, Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}