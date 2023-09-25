package com.padcmyanmar.mewz.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.databinding.ViewHolderShowcaseBinding
import com.padcmyanmar.mewz.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.utils.IMAGE_BASE_URL

class ShowCaseViewHolder(itemView: View, private val delegate: ShowcaseViewHolderDelegate)
    : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderShowcaseBinding

    private var mMovie: MovieVO? = null

    init {
        binding = ViewHolderShowcaseBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapMovieFromShowcase(mMovie?.id ?: 0)
        }
    }

    fun bindData(movie: MovieVO) {

        mMovie = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivShowCase)

        binding.tvShowCaseMovieName.text = movie.originalTitle
        binding.tvShowCaseMovieDate.text = movie.releaseDate
    }
}