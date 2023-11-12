package com.atfotiad.pokemoncompose.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Other(
    @Embedded @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)