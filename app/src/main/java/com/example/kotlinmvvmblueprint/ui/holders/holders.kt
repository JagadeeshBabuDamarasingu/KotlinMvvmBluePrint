package com.example.kotlinmvvmblueprint.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinmvvmblueprint.Category
import com.example.kotlinmvvmblueprint.Video
import com.example.kotlinmvvmblueprint.databinding.ItemCategoryBinding
import com.example.kotlinmvvmblueprint.databinding.ItemVideoBinding
import timber.log.Timber

class CategoryHolder(
    private val binding: ItemCategoryBinding,
    val listener: CategoryHolderListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindCategory(category: Category) {
        Timber.e("binding %s", category)
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
    val listener: VideoHolderListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindVideo(video:  Video) {
        with(binding) {
            thumbImage.clipToOutline = true
            Glide.with(root.context)
                .load(video.thumbUrl)
                .into(thumbImage)
            thumbImage.setOnClickListener {
                listener.onCategoryClicked(video)
            }
        }
    }

    interface VideoHolderListener {
        fun onCategoryClicked(category: Video)
    }
}