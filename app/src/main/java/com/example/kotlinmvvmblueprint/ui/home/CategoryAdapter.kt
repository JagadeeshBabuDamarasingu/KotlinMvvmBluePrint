package com.example.kotlinmvvmblueprint.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmblueprint.Category
import com.example.kotlinmvvmblueprint.databinding.ItemCategoryBinding
import com.example.kotlinmvvmblueprint.ui.holders.CategoryHolder

class CategoryAdapter(val listener: CategoryHolder.CategoryHolderListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var categoryList = mutableListOf<Category>()

    fun addCategories(categories: List<Category>) {
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createCategoryHolder(parent)
    }

    private fun createCategoryHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoryHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), listener
        )
    }


    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryHolder) holder.bindCategory(categoryList[holder.adapterPosition])
    }


}