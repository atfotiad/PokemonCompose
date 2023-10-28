package com.atfotiad.pokemoncompose.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.atfotiad.pokemoncompose.api.PokeClient
import com.atfotiad.pokemoncompose.model.Pokemon
import com.atfotiad.pokemoncompose.model.Result
import retrofit2.HttpException
import java.io.IOException

internal const val loadSize = 20
private val initialPokePageIndex = PagingIndex(limit = loadSize, offset = 0, total = 0)

class PokeDataSource(
    private val pokeApi: PokeClient
) : PagingSource<PagingIndex, Pokemon>() {
    override suspend fun load(params: LoadParams<PagingIndex>): LoadResult<PagingIndex, Pokemon> {
        val limit = params.key?.limit ?: initialPokePageIndex.limit
        val offset = params.key?.offset ?: initialPokePageIndex.offset

        val finalPokeList: ArrayList<Pokemon> = ArrayList()
        return try {
            val response = pokeApi.getAllPokemon(offset, limit)
            val pokemonList = response.body()?.results ?: ArrayList()

            for (pokemon: Result in pokemonList) {

                val tokens = pokemon.url.split("/")
                for (token: String in tokens) {
                    Log.d("token", "load: $token")
                }
                val id = tokens[tokens.lastIndex - 1].toInt()
                val details = pokeApi.getPokemonInfo(id)
                val pokedexEntryText = pokeApi.getSpeciesEntry(id)
                details.body()?.pokeDexEntry =
                    pokedexEntryText.body()?.flavorTextEntries?.firstOrNull {
                        it.language.name == "en"
                    }?.flavorText.toString()
                Log.d("pokemonList", "load: " + (pokemon.name))
                Log.d("pokemonList", "load: " + (details.body()?.pokeDexEntry))
                Log.d(
                    "pokemonList",
                    "load: image URL: " + (details.body()?.sprites?.other?.officialArtwork?.frontDefault)
                )
                Log.d("pokemonList", "load: "+ details.body()?.types)
                finalPokeList.add(details.body()!!)
            }


            val (previousKey, nextKey) = initialPokePageIndex.pagingIndexing(
                offset = offset,
                totalFromServer = response.body()!!.count,
                totalFromPaging = params.key?.total ?: 0
            )

            LoadResult.Page(
                data = finalPokeList,
                prevKey = previousKey,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<PagingIndex, Pokemon>): PagingIndex? {
        return null
    }

}