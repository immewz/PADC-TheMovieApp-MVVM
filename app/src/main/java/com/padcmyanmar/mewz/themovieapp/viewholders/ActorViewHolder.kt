package com.padcmyanmar.mewz.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.databinding.ViewHolderActorBinding
import com.padcmyanmar.mewz.themovieapp.utils.IMAGE_BASE_URL

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderActorBinding

    init {
        binding = ViewHolderActorBinding.bind(itemView)
    }

    fun bindData(actor: ActorVO) {

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(binding.ivBestActor)

        binding.tvActorName.text = actor.name
    }
}