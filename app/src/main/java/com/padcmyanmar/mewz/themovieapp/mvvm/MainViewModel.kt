package com.padcmyanmar.mewz.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {

    // Model
    private var mMovieModel: MovieModel = MovieModelImpl

    // LiveData
    var nowPlayingMoviesLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    val genreLiveData =  MutableLiveData<List<GenreVO>>()
    val moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    val actorLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        nowPlayingMoviesLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenreList(
            onSuccess = {
                genreLiveData.postValue(it)
                getMoviesByGenre(0)
            }, onFailure = { mErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorLiveData.postValue(it)
            }, onFailure = { mErrorLiveData.postValue(it) }
        )
    }

    fun getMoviesByGenre(genrePosition: Int){
        genreLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(it,
            onSuccess = { moviesByGenre -> moviesByGenreLiveData.postValue(moviesByGenre) },
            onFailure = { mErrorLiveData.postValue(it)} )
        }
    }

}