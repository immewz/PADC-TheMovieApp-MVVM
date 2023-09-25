package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO

class GenreListTypeConverter {

    @TypeConverter
    fun toString(genreList: List<GenreVO>?): String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreVO(genreListJsonStr: String): List<GenreVO>?{
        val genreListVOType = object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(genreListJsonStr, genreListVOType)
    }
}