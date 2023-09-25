package com.padcmyanmar.mewz.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.databinding.ViewHolderMovieBinding
import com.padcmyanmar.mewz.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.utils.IMAGE_BASE_URL

class MovieViewHolder(itemView: View, private val delegate: MovieViewHolderDelegate)
    : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderMovieBinding

    private var mMovie: MovieVO? = null

    init {
        binding = ViewHolderMovieBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapMovie(mMovie?.id ?: 0)
        }
    }

    fun bindData(movie: MovieVO) {

        mMovie = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieImage)

        binding.tvMovieName.text = movie.originalTitle
        binding.tvMovieRating.text = movie.voteAverage?.toString()
        binding.rbMovieRating.rating = movie.getRatingBaseOnFiveStars()
    }
}