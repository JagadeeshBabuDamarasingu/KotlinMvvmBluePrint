package com.example.kotlinmvvmblueprint.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.kotlinmvvmblueprint.AppConstants
import com.example.kotlinmvvmblueprint.BuildConfig
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesDataRepository(
        context: Context,
        prefManager: PrefManager,
        databaseManager: DatabaseManager,
        apiService: ApiService
    ): DataRepository = DataRepositoryImpl(context, prefManager, databaseManager, apiService)

    @Provides
    @Singleton
    fun providesDatabaseManager(): DatabaseManager = DatabaseManagerImpl()

    @Provides
    @Singleton
    fun providesPrefManager(sharedPreferences: SharedPreferences): PrefManager = PrefManagerImpl(sharedPreferences)

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

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

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        context: Context,
        prefManager: PrefManager
    ): OkHttpClient {
        var okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(interceptor)
        }

        okHttpBuilder.addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer " + prefManager.getToken())
                    .build()
            )
        }

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}