package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.themovieapp.data.vos.SpokenLanguagesVO

class SpokenLanguagesListTypeConverter {

    @TypeConverter
    fun toString(spokenLanguagesList: List<SpokenLanguagesVO>?): String{
        return Gson().toJson(spokenLanguagesList)
    }

    @TypeConverter
    fun toSpokenLanguagesVO(spokenLanguagesListJsonStr: String): List<SpokenLanguagesVO>?{
        val spokenLanguagesListVOType = object : TypeToken<List<SpokenLanguagesVO>?>(){}.type
        return Gson().fromJson(spokenLanguagesListJsonStr, spokenLanguagesListVOType)
    }
}