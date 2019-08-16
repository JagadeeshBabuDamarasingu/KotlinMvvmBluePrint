package com.example.kotlinmvvmblueprint.data.local.prefs

interface PrefManager {

    fun getToken(): String?
    fun saveToken(token: String)
}