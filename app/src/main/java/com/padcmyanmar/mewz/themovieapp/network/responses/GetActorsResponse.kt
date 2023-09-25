package com.padcmyanmar.mewz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO

data class GetActorsResponse(

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<ActorVO>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?
)
