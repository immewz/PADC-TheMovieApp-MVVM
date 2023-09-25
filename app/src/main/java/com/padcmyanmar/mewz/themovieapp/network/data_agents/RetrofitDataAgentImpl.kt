//package com.padcmyanmar.mewz.themovieapp.network.data_agents
//
//import android.util.Log
//import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
//import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
//import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
//import com.padcmyanmar.mewz.themovieapp.network.TheMovieAPI
//import com.padcmyanmar.mewz.themovieapp.network.responses.GetActorsResponse
//import com.padcmyanmar.mewz.themovieapp.network.responses.GenreListResponse
//import com.padcmyanmar.mewz.themovieapp.network.responses.GetCreditsByMovieResponse
//import com.padcmyanmar.mewz.themovieapp.network.responses.MovieListResponse
//import com.padcmyanmar.mewz.themovieapp.utils.BASE_URL
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl: MovieDataAgent {
//
//    private var mTheMovieAPI: TheMovieAPI? = null
//
//    init {
//
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15,TimeUnit.SECONDS)
//            .readTimeout(15,TimeUnit.SECONDS)
//            .writeTimeout(15,TimeUnit.SECONDS)
//            .addInterceptor(loggingInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mTheMovieAPI = retrofit.create(TheMovieAPI::class.java)
//    }
//
//
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieAPI?.getNowPlayingMovies()
//            ?.enqueue(object : Callback<MovieListResponse>{
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//                        Log.d("Retrofit","data.${movieList}")
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//            })
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieAPI?.getPopularMovies()
//            ?.enqueue(object : Callback<MovieListResponse>{
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//                        Log.d("Retrofit","data.${movieList}")
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//            })
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieAPI?.getTopRatedMovies()
//            ?.enqueue(object : Callback<MovieListResponse>{
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//            })
//    }
//
//    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieAPI?.getGenreList()
//            ?.enqueue(object : Callback<GenreListResponse>{
//                override fun onResponse(
//                    call: Call<GenreListResponse>,
//                    response: Response<GenreListResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val genreList = response.body()?.genres ?: listOf()
//                        onSuccess(genreList)
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<GenreListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//
//            })
//    }
//
//    override fun getMoviesByGenre(
//        genreId: Int,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieAPI?.getMoviesByGenre(genreId)
//            ?.enqueue(object : Callback<MovieListResponse>{
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val movieList = response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//
//            })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        mTheMovieAPI?.getActors()
//            ?.enqueue(object : Callback<GetActorsResponse>{
//                override fun onResponse(
//                    call: Call<GetActorsResponse>,
//                    response: Response<GetActorsResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val actorList = response.body()?.results ?: listOf()
//                        onSuccess(actorList)
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<GetActorsResponse>, t: Throwable) {
//                    onFailure(t.message ?: "" )
//                }
//
//            })
//    }
//
//    override fun getMovieDetail(
//        movieId: Int,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieAPI?.getMovieDetail(movieId)
//            ?.enqueue(object : Callback<MovieVO>{
//                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
//                    if (response.isSuccessful){
//                        response.body()?.let {
//                            onSuccess(it)
//                        }
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//                    onFailure(t.message ?: "" )
//                }
//
//
//            })
//    }
//
//    override fun getCreditsByMovie(
//        movieId: Int,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieAPI?.getCreditsByMovie(movieId)
//            ?.enqueue(object : Callback<GetCreditsByMovieResponse>{
//                override fun onResponse(
//                    call: Call<GetCreditsByMovieResponse>,
//                    response: Response<GetCreditsByMovieResponse>
//                ) {
//                    if (response.isSuccessful){
//                        response.body()?.let {
//                            onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
//                        }
//                    }else{
//                        onFailure(response.message().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<GetCreditsByMovieResponse>, t: Throwable) {
//                    onFailure(t.message ?: "" )
//                }
//
//            })
//    }
//}