package com.atfotiad.pokemoncompose.api

import com.atfotiad.pokemoncompose.model.PokedexEntryText
import com.atfotiad.pokemoncompose.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeClient {
    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("offset")
        offset: Int = 0,
        @Query("limit")
        limit: Int = 100): Response<BaseResponse>


    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id :Int
    ) : Response<Pokemon>

    @GET("pokemon-species/{id}")
    suspend fun getSpeciesEntry(
        @Path("id") id :Int
    ) : Response<PokedexEntryText>
}