package com.example.kotlinmvvmblueprint.ui.social

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmblueprint.databinding.ItemPostViewBinding
import com.example.kotlinmvvmblueprint.ui.social.model.Feed

class SocialFeedAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_POST = 1
    }

    private val feedList = mutableListOf<Feed>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> createHeader(parent)
            VIEW_TYPE_POST -> createPostHolder(parent)
            else -> throw IllegalArgumentException("Unsupported ViewType!")
        }
    }

    private fun createPostHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PostHolder(
            ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    private fun createHeader(parent: ViewGroup): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            RecyclerView(parent.context)
                .apply {
                    layoutManager =
                        LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = HeaderAdapter()
                    setPadding(8, 16, 8, 16)
                }
        ) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_POST
    }

    override fun getItemCount(): Int = feedList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(holder.adapterPosition)) {
            VIEW_TYPE_HEADER -> bindHeader(holder)
            VIEW_TYPE_POST -> (holder as PostHolder).bindFeed(feedList[holder.adapterPosition - 1])
            else -> throw IllegalArgumentException("Unsupported ViewType!")
        }
    }


    private fun bindHeader(holder: RecyclerView.ViewHolder) {
//        with(holder.itemView as RecyclerView) {
//            layoutManager
//        }


    }

    fun addItems(feedList: MutableList<Feed>) {
        this.feedList.addAll(feedList)
        notifyDataSetChanged()
    }

}