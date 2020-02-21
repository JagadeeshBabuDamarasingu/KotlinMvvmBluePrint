package com.example.kotlinmvvmblueprint.ui.social

import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.ui.social.model.imageList

class HeaderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            AppCompatImageView(parent.context)
                .apply {
                    background = parent.context.getDrawable(R.drawable.image_bg)
                    scaleType = ImageView.ScaleType.CENTER
                    layoutParams = ViewGroup.MarginLayoutParams(200,300)
                        .apply {
                            setMargins(8,0,8,0)
                        }
                    clipToOutline = true
                }
        ) {}


    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.adapterPosition) {
            0 -> bindPostStory(holder)
            else -> bindStory(holder)
        }
    }

    private fun bindPostStory(holder: RecyclerView.ViewHolder) {
        with(holder.itemView as AppCompatImageView) {
            background = context.getDrawable(R.drawable.post_story_bg)
            isClickable = true
            isFocusable = true
            Glide.with(context)
                .load(R.drawable.ic_add)
                .into(this)
        }
    }

    private fun bindStory(holder: RecyclerView.ViewHolder) {
        with(holder.itemView as AppCompatImageView) {
            Glide.with(context)
                .load(imageList.random())
                .apply(
                    RequestOptions.circleCropTransform()
                        .centerCrop()
                )
                .into(this)
        }
    }
}