package com.example.kotlinmvvmblueprint.data

import com.example.kotlinmvvmblueprint.data.local.db.DatabaseManager
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManager
import com.example.kotlinmvvmblueprint.data.network.ApiService

abstract class DataRepository : PrefManager, DatabaseManager {


}