package com.atfotiad.pokemoncompose.di

import android.content.Context
import androidx.room.Room
import com.atfotiad.pokemoncompose.db.PokemonDatabase
import com.atfotiad.pokemoncompose.utils.Converters
import com.atfotiad.pokemoncompose.utils.GsonParser
import com.atfotiad.pokemoncompose.utils.JsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PokemonDatabase::class.java, "POKEMON_DATABASE.db"
    ).addTypeConverter(Converters(GsonParser(Gson()))).build()

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDatabase) = database.PokemonDao()
}