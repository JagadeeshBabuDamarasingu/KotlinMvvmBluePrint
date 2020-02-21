package com.example.kotlinmvvmblueprint.ui.social

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.databinding.ItemPostViewBinding
import com.example.kotlinmvvmblueprint.ui.social.model.Feed

fun getCircleTransform(): RequestOptions = RequestOptions.circleCropTransform()
    .format(DecodeFormat.PREFER_ARGB_8888)
    .override(
        Target.SIZE_ORIGINAL,
        Target.SIZE_ORIGINAL
    )
    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)


class PostHolder(private val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root) {
    lateinit var  feed: Feed
    init {
        with(binding){
            favoriteView.setOnClickListener {
                feed.isFavorite = !feed.isFavorite
                setFavorite(feed.isFavorite,root.context)
            }

        }

    }

    private fun setFavorite(isFavorite: Boolean, context: Context) {
        binding.favoriteView.setImageDrawable(
            context.getDrawable(if (isFavorite) R.drawable.ic_favorite_solid else R.drawable.ic_favorite_border)
        )

    }

    fun bindFeed(feed: Feed) {
        this.feed = feed
        with(binding) {
            image.clipToOutline = true
            userAvatar.clipToOutline = true
            userName.text = feed.user.name
            setFavorite(feed.isFavorite,root.context)

            Glide.with(image)
                .load(feed.postImage)
                .apply(RequestOptions.centerCropTransform())
                .into(image)
            Glide.with(userAvatar)
                .load(feed.user.avatarUrl)
                .apply(getCircleTransform())
                .into(userAvatar)
        }
    }

}