package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.themovieapp.data.vos.ProductionCompaniesVO

class ProductionCompaniesListTypeConverter {

    @TypeConverter
    fun toString(productionCompaniesList: List<ProductionCompaniesVO>?): String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompaniesVO(productionCompaniesListJsonStr: String): List<ProductionCompaniesVO>?{
        val productionCompaniesListVOType = object : TypeToken<List<ProductionCompaniesVO>?>(){}.type
        return Gson().fromJson(productionCompaniesListJsonStr, productionCompaniesListVOType)
    }
}