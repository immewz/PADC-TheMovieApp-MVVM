package com.padcmyanmar.mewz.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.mewz.themovieapp.R
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.viewholders.ActorViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {

    private var mActorList: List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mActorList.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        if (mActorList.isNotEmpty()){
            holder.bindData(mActorList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorList: List<ActorVO>) {
        mActorList = actorList
        notifyDataSetChanged()
    }
}