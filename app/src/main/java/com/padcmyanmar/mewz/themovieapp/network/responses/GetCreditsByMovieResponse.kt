package com.padcmyanmar.mewz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO

data class GetCreditsByMovieResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("cast")
    val cast: List<ActorVO>?,

    @SerializedName("crew")
    val crew: List<ActorVO>?
)
