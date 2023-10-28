package com.atfotiad.pokemoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.atfotiad.pokemoncompose.PokemonComposeApplication.Companion.PokeApp
import com.atfotiad.pokemoncompose.ui.theme.PokemonComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonComposeTheme {
                PokeApp()
            }
        }
    }
}
