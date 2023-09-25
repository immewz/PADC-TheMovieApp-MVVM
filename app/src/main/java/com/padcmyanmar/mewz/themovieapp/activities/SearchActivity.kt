package com.padcmyanmar.mewz.themovieapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.mewz.themovieapp.R
import com.padcmyanmar.mewz.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.databinding.ActivitySearchBinding
import com.padcmyanmar.mewz.themovieapp.delegates.MovieViewHolderDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class SearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    private lateinit var binding: ActivitySearchBinding

    private lateinit var mMovieAdapter: MovieAdapter

    private var mMovieModel: MovieModel = MovieModelImpl

    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, SearchActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.edtSearchMovie.textChanges()
            .debounce(500L , TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.getSearchMovie(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            }, {
                showError(it.localizedMessage ?: "")
            })
    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        binding.rvMovieList.adapter= mMovieAdapter
        binding.rvMovieList.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}