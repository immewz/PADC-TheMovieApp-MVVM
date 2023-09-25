//package com.padcmyanmar.mewz.themovieapp.network.data_agents
//
//import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
//import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
//import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
//
//interface MovieDataAgent {
//
//    fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getGenreList(
//        onSuccess: (List<GenreVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getMoviesByGenre(
//        genreId: Int,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getActors(
//        onSuccess: (List<ActorVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getMovieDetail(
//        movieId: Int,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getCreditsByMovie(
//        movieId: Int,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//}