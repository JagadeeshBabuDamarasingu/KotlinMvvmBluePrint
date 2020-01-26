package com.example.kotlinmvvmblueprint

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String
)

data class Video(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnailURL")
    val thumbUrl: String,
    @SerializedName("videoURL")
    val videoUrl: String,
    @SerializedName("categories")
    val categoryList: String
)

data class CategoriesResponse(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("response")
    val response: CategoryResponseObject =
        CategoryResponseObject(mapOf())
)

data class CategoryResponseObject(
    @SerializedName("videoCategories")
    val videoCategories: Map<String, Category>
)

data class VideosResponse(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("response")
    val response: VideoResponseObject =
        VideoResponseObject(mapOf())
)

data class VideoResponseObject(
    @SerializedName("videos")
    val videos: Map<String, Video>

)

