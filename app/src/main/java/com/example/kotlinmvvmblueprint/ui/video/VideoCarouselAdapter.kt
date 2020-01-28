package com.example.kotlinmvvmblueprint.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmblueprint.Video
import com.example.kotlinmvvmblueprint.databinding.ItemVideoBinding
import com.example.kotlinmvvmblueprint.ui.holders.VideoThumbHolder

class VideoCarouselAdapter(private val listener: VideoThumbHolder.VideoHolderListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var categoryList = mutableListOf<Video>()

    fun addVideos(categories: List<Video>) {
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createCategoryHolder(parent)
    }

    private fun createCategoryHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VideoThumbHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), listener
        )
    }


    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoThumbHolder) holder.bindVideo(categoryList[holder.adapterPosition])
    }

}