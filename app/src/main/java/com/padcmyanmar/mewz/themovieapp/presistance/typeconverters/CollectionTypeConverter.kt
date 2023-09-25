package com.padcmyanmar.mewz.themovieapp.presistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.mewz.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {

    @TypeConverter
    fun toString(collection: CollectionVO?): String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(collectionListToJsonStr: String): CollectionVO?{
        val collectionVOType = object :TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(collectionListToJsonStr, collectionVOType)
    }
}