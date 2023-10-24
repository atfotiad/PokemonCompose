package com.atfotiad.pokemoncompose.api

import com.atfotiad.pokemoncompose.model.Result


data class BaseResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)