package com.atfotiad.pokemoncompose.model

import com.google.gson.annotations.SerializedName

data class PokedexEntryText(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries :ArrayList<FlavorText>
)
