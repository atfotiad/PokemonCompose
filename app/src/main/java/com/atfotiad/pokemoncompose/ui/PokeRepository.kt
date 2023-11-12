package com.atfotiad.pokemoncompose.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.atfotiad.pokemoncompose.api.PokeClient
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class PokeRepository @Inject constructor(
    private val pokeClient: PokeClient,
    val localDataSource: LocalDataSource
) {

    init {
        client = pokeClient
        local = localDataSource
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
        private lateinit var local: LocalDataSource

        fun getInstance(): PokeRepository = synchronized(this) {
            instance ?: PokeRepository(client, local).also { instance = it }
        }
    }
}