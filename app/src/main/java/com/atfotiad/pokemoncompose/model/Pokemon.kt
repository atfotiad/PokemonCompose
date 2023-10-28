package com.atfotiad.pokemoncompose.model

data class Pokemon(
    val name: String,
    val sprites: Sprites,
    val types: ArrayList<Type>,
    var pokeDexEntry: String
)
