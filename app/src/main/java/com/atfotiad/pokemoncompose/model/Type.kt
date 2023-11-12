package com.atfotiad.pokemoncompose.model

import androidx.room.TypeConverters
import com.atfotiad.pokemoncompose.utils.Converters

data class Type(
    val slot: Int,
    @TypeConverters(Converters::class) val type: TypeX
)