package com.atfotiad.pokemoncompose.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.atfotiad.pokemoncompose.model.Type
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {
    @TypeConverter
    fun toTypeJson(meaning: List<Type>) : String {
        return jsonParser.toJson(
            meaning,
            object : TypeToken<ArrayList<Type>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTypesToJson(json: String): ArrayList<Type>{
        return jsonParser.fromJson<ArrayList<Type>>(
            json,
            object: TypeToken<ArrayList<Type>>(){}.type
        ) ?: ArrayList()
    }
}