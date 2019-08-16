package com.example.kotlinmvvmblueprint.data.local.db

import android.content.Context
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManager
import com.example.kotlinmvvmblueprint.data.network.ApiService

class DatabaseManagerImpl(
    private val context: Context,
    private val prefManager: PrefManager,
    private val databaseManager: DatabaseManager,
    private val apiService: ApiService
) : DatabaseManager {


}