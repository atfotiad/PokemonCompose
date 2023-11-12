package com.atfotiad.pokemoncompose.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.atfotiad.pokemoncompose.utils.Converters

@Entity(tableName = "pokemon_table")
data class Pokemon(
    @PrimaryKey val name: String,
    @Embedded("sprite_img_") val sprites: Sprites,
    @TypeConverters(Converters::class) val types: ArrayList<Type>,
    var pokeDexEntry: String
)
