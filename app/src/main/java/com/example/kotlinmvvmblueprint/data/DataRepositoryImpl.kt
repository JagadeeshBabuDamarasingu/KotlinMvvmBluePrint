package com.example.kotlinmvvmblueprint.data

import android.content.Context
import com.example.kotlinmvvmblueprint.data.local.db.DatabaseManager
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManager
import com.example.kotlinmvvmblueprint.data.network.ApiService
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val context: Context,
    private val prefManager: PrefManager,
    private val databaseManager: DatabaseManager,
    private val apiService: ApiService
) : DataRepository() {

    override fun getToken(): String? = prefManager.getToken()

    override fun saveToken(token: String) = prefManager.saveToken(token)

}