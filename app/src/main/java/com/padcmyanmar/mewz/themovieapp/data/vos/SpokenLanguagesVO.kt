package com.padcmyanmar.mewz.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesVO(

    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val iso_639_1: String?,

    @SerializedName("name")
    val name: String?
)
