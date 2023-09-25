package com.padcmyanmar.mewz.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.databinding.ViewHolderBannerBinding
import com.padcmyanmar.mewz.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.utils.IMAGE_BASE_URL

class BannerViewHolder(itemView: View, private val delegate: BannerViewHolderDelegate)
    : RecyclerView.ViewHolder(itemView) {


    private var binding: ViewHolderBannerBinding

    private var mMovie: MovieVO? = null

    init {
        binding = ViewHolderBannerBinding.bind(itemView)

        itemView.setOnClickListener {
            delegate.onTapMovieFromBanner(mMovie?.id ?: 0)
        }
    }

    fun bindData(movie: MovieVO) {

        mMovie = movie

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(binding.ivBannerImage)

        binding.tvBannerMovieName.text = movie.originalTitle
    }
}