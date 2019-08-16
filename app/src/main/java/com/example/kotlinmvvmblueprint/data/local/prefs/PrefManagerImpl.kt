package com.example.kotlinmvvmblueprint.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject

class PrefManagerImpl @Inject constructor(private val mSharedPreferences: SharedPreferences) : PrefManager {

    private val API_TOKEN: String = "PREF_KEY_API_TOKEN";


    override fun getToken(): String? {
        return mSharedPreferences.getString(API_TOKEN,null);
    }

    override fun saveToken(token: String) {
        mSharedPreferences.edit().putString(API_TOKEN,token).apply()
    }


}