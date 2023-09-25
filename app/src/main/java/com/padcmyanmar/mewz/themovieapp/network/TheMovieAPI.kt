package com.padcmyanmar.mewz.themovieapp.network

import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.network.responses.GetActorsResponse
import com.padcmyanmar.mewz.themovieapp.network.responses.GenreListResponse
import com.padcmyanmar.mewz.themovieapp.network.responses.GetCreditsByMovieResponse
import com.padcmyanmar.mewz.themovieapp.network.responses.MovieListResponse
import com.padcmyanmar.mewz.themovieapp.utils.API_GET_GENRE_LIST
import com.padcmyanmar.mewz.themovieapp.utils.API_GET_MOVIES_BY_GENRE
import com.padcmyanmar.mewz.themovieapp.utils.API_GET_NOW_PLAYING_MOVIES
import com.padcmyanmar.mewz.themovieapp.utils.API_GET_POPULAR_MOVIES
import com.padcmyanmar.mewz.themovieapp.utils.API_GET_TOP_RATED_MOVIES
import com.padcmyanmar.mewz.themovieapp.utils.API_MOVIE_CREDITS_BY_MOVIE_ID
import com.padcmyanmar.mewz.themovieapp.utils.API_MOVIE_DETAIL_BY_MOVIE_ID
import com.padcmyanmar.mewz.themovieapp.utils.API_PEOPLE_POPULAR
import com.padcmyanmar.mewz.themovieapp.utils.API_SEARCH_MOVIE
import com.padcmyanmar.mewz.themovieapp.utils.MOVIE_API_KEY
import com.padcmyanmar.mewz.themovieapp.utils.PARAM_API_KEY
import com.padcmyanmar.mewz.themovieapp.utils.PARAM_QUERY
import com.padcmyanmar.mewz.themovieapp.utils.PARAM_WITH_GENRES
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieAPI {

    @GET(API_GET_NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<MovieListResponse>

    @GET(API_GET_GENRE_LIST)
    fun getGenreList(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<GenreListResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_WITH_GENRES) genreId: Int,
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<MovieListResponse>

    @GET(API_PEOPLE_POPULAR)
    fun getActors(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<GetActorsResponse>

    @GET("$API_MOVIE_DETAIL_BY_MOVIE_ID/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<MovieVO>

    @GET("${API_MOVIE_CREDITS_BY_MOVIE_ID}/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: Int,
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY
    ): Observable<GetCreditsByMovieResponse>

    @GET(API_SEARCH_MOVIE)
    fun getSearchMovie(
        @Query(PARAM_API_KEY) api_key: String = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query: String
    ): Observable<MovieListResponse>
}