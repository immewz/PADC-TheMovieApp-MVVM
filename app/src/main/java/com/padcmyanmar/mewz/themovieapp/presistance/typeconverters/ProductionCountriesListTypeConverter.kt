package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.themovieapp.data.vos.ProductionCountriesVO

class ProductionCountriesListTypeConverter {

    @TypeConverter
    fun toString(productionCountriesList: List<ProductionCountriesVO>?): String{
        return Gson().toJson(productionCountriesList)
    }

    @TypeConverter
    fun toProductionCountriesVO(productionCountriesListJsonStr: String): List<ProductionCountriesVO>?{
        val productionCountriesListVOType = object : TypeToken<List<ProductionCountriesVO>?>(){}.type
        return Gson().fromJson(productionCountriesListJsonStr, productionCountriesListVOType)
    }
}