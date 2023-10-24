package com.atfotiad.pokemoncompose.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.atfotiad.pokemoncompose.api.PokeClient
import javax.inject.Inject

class PokeRepository @Inject constructor(private val pokeClient: PokeClient) {

    init {
        client = pokeClient
    }

    fun getPokeResults() =
        Pager(
            config = PagingConfig(
                pageSize = loadSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokeDataSource(pokeClient) }
        ).flow


    companion object {
        private var instance: PokeRepository? = null
        private lateinit var client: PokeClient

        fun getInstance(): PokeRepository = synchronized(this) {
            instance ?: PokeRepository(client).also { instance = it }
        }
    }
}