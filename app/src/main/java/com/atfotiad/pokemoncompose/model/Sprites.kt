package com.atfotiad.pokemoncompose.model

import androidx.room.Embedded

data class Sprites(
    @Embedded("other_") val other: Other
)