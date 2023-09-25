package com.padcmyanmar.mewz.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO

class MovieDetailViewModel: ViewModel() {

    // Model
    private val mMovieModel = MovieModelImpl

    // Live Data
    var movieDetailLiveData: LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId: Int){
        movieDetailLiveData = mMovieModel.getMovieDetail(movieId){ mErrorLiveData.postValue(it) }

        mMovieModel.getCreditsByMovie(movieId,
        onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue( it.second ?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
            })
    }
}