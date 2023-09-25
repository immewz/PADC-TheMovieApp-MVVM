package com.padcmyanmar.mewz.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.themovieapp.data.vos.DateVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO

data class MovieListResponse(

    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?
)
