package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {

    @TypeConverter
    fun toString(genresIdList: List<Int>?): String{
        return Gson().toJson(genresIdList)
    }

    @TypeConverter
    fun toGenreIds(genreIdListJsonStr: String): List<Int>? {
        val genresIdListType = object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(genreIdListJsonStr, genresIdListType)
    }
}