package com.example.kotlinmvvmblueprint.ui.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinmvvmblueprint.Category
import com.example.kotlinmvvmblueprint.Video
import com.example.kotlinmvvmblueprint.databinding.ItemCategoryBinding
import com.example.kotlinmvvmblueprint.databinding.ItemVideoBinding

class CategoryHolder(
    private val binding: ItemCategoryBinding,
    private val listener: CategoryHolderListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindCategory(category: Category) {
        with(binding) {
            thumbImage.clipToOutline = true
            Glide.with(root.context)
                .load(category.imageUrl)
                .into(thumbImage)
            thumbImage.setOnClickListener {
                listener.onCategoryClicked(category)
            }
        }
    }

    interface CategoryHolderListener {
        fun onCategoryClicked(category: Category)
    }
}

class VideoThumbHolder(
    private val binding: ItemVideoBinding,
    private val listener: VideoHolderListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindVideo(video: Video) {
        with(binding) {
            rootLayout.clipToOutline = true
            thumbImage.clipToOutline = true
            Glide.with(root.context)
                .load(video.thumbUrl)
                .into(thumbImage)
            thumbImage.setOnClickListener {
                video.isSeen = true
                listener.onCategoryClicked(video)
                setSeenStatus(true)
            }
            setSeenStatus(video.isSeen)
        }
    }

    private fun setSeenStatus(isSeen: Boolean) {
        binding.indicator.visibility = if (isSeen) View.VISIBLE else View.GONE
    }

    interface VideoHolderListener {
        fun onCategoryClicked(video: Video)
    }
}