package com.example.kotlinmvvmblueprint.data.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    fun test(): Single<Response<Void>>
}