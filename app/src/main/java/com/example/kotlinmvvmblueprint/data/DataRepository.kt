package com.example.kotlinmvvmblueprint.data

import com.example.kotlinmvvmblueprint.data.local.db.DatabaseManager
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManager

abstract class DataRepository : PrefManager, DatabaseManager {


}