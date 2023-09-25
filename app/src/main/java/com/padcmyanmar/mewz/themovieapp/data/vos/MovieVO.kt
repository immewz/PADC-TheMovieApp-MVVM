package com.padcmyanmar.mewz.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.CollectionTypeConverter
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.GenreIdsTypeConverter
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.GenreListTypeConverter
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.ProductionCompaniesListTypeConverter
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.ProductionCountriesListTypeConverter
import com.padcmyanmar.mewz.themovieapp.presistance.typeconverters.SpokenLanguagesListTypeConverter


@Entity("movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompaniesListTypeConverter::class,
    ProductionCountriesListTypeConverter::class,
    SpokenLanguagesListTypeConverter::class
)
data class MovieVO(

    @SerializedName("adult")
    @ColumnInfo("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo("backdrop_path")
    val backdropPath: String?,

    @SerializedName("belongs_to_collection")
    @ColumnInfo("belongs_to_collection")
    val belongsToCollection: CollectionVO?,

    @SerializedName("budget")
    @ColumnInfo("budget")
    val budget: Int?,

    @SerializedName("genre_ids")
    @ColumnInfo("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("genres")
    @ColumnInfo("genres")
    val genres: List<GenreVO>?,

    @SerializedName("homepage")
    @ColumnInfo("homepage")
    val homepage: String?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("imdb_id")
    @ColumnInfo("imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    @ColumnInfo("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo("overview")
    val overview: String?,

    @SerializedName("popularity")
    @ColumnInfo("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    @ColumnInfo("production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    @ColumnInfo("production_countries")
    val productionCountries: List<ProductionCountriesVO>?,

    @SerializedName("release_date")
    @ColumnInfo("release_date")
    val releaseDate: String?,

    @SerializedName("revenue")
    @ColumnInfo("revenue")
    val revenue: Int?,

    @SerializedName("runtime")
    @ColumnInfo("runtime")
    val runtime: Int,

    @SerializedName("spoken_languages")
    @ColumnInfo("spoken_languages")
    val spokenLanguageVOS: List<SpokenLanguagesVO>?,

    @SerializedName("status")
    @ColumnInfo("status")
    val status: String?,

    @SerializedName("tagline")
    @ColumnInfo("tagline")
    val tagline: String?,

    @SerializedName("title")
    @ColumnInfo("title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo("vote_average")
    val voteAverage: Float?,

    @SerializedName("vote_count")
    @ColumnInfo("vote_count")
    val voteCount: Int?,

    @ColumnInfo("type")
    var type: String?

) {
    fun getRatingBaseOnFiveStars(): Float {
        return voteAverage?.div(2) ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }

    fun getProductionCountriesAsCommaSeparatedString(): String {
        return productionCountries?.map { it.name }?.joinToString(",") ?: ""
    }
}

const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"
