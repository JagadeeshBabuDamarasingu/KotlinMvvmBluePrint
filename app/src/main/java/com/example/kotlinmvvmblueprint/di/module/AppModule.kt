package com.example.kotlinmvvmblueprint.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinmvvmblueprint.AppConstants
import com.example.kotlinmvvmblueprint.data.DataBaseInfo
import com.example.kotlinmvvmblueprint.data.DataRepository
import com.example.kotlinmvvmblueprint.data.DataRepositoryImpl
import com.example.kotlinmvvmblueprint.data.PreferenceInfo
import com.example.kotlinmvvmblueprint.data.local.db.DatabaseManager
import com.example.kotlinmvvmblueprint.data.local.db.DatabaseManagerImpl
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManager
import com.example.kotlinmvvmblueprint.data.local.prefs.PrefManagerImpl
import com.example.kotlinmvvmblueprint.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesDataRepository(repository: DataRepositoryImpl): DataRepository = repository

    @Provides
    @Singleton
    fun providesDatabaseManager(databaseManager: DatabaseManagerImpl): DatabaseManager = databaseManager

    @Provides
    @Singleton
    fun providesPrefManager(prefManager: PrefManagerImpl): PrefManager = prefManager

    @Provides
    @Singleton
    fun providesApiService(apiService: ApiService): ApiService = apiService

    @Provides
    @DataBaseInfo
    fun providesDatabaseName(): String = AppConstants.DB_NAME

    @Provides
    @PreferenceInfo
    fun providesPrefName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    fun providesSharedPreferences(
        context: Context,
        @PreferenceInfo prefName: String
    ): SharedPreferences {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }


}