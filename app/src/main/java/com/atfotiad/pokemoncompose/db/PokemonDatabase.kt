package com.atfotiad.pokemoncompose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.utils.Converters

@Database(entities = [Pokemon::class], version = 1, exportSchema = true)
@TypeConverters( value = [Converters::class])
 abstract class PokemonDatabase: RoomDatabase() {
     abstract fun PokemonDao() : PokemonDao
}