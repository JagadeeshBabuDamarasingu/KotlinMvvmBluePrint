package com.example.kotlinmvvmblueprint.data.network

import com.example.kotlinmvvmblueprint.CategoriesResponse
import com.example.kotlinmvvmblueprint.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(EndPoints.GET_CATEGORIES)
    fun getCategories(): Single<CategoriesResponse>

    @GET(EndPoints.GET_VIDEOS)
    fun getVideos(): Single<VideosResponse>

}