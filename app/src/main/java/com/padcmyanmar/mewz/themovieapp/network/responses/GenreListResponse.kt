package com.padcmyanmar.mewz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO

data class GenreListResponse(

    @SerializedName("genres")
    val genres: List<GenreVO>?
)
