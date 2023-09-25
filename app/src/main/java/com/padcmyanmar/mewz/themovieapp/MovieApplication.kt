package com.padcmyanmar.mewz.themovieapp

import android.app.Application
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}