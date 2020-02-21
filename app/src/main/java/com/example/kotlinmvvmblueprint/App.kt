package com.example.kotlinmvvmblueprint

import android.content.Context
import com.example.kotlinmvvmblueprint.di.component.DaggerAppComponent
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.MoEngage
import com.moengage.core.model.AppStatus
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class App : DaggerApplication() {

    lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initMoEngage()
    }

    private fun initMoEngage() {
        MoEngage.initialise(
            MoEngage.Builder(this, BuildConfig.MOENGAGE_APP_ID)
                .build()
        )
        trackInstallOrUpdate()
    }

    private fun trackInstallOrUpdate() {
        val preferences = getSharedPreferences(AppConstants.PREF_NAME, 0)
        var appStatus = AppStatus.INSTALL
        if (preferences.getBoolean("has_sent_install", false)) {
            if (preferences.getBoolean("existing", false)) {
                appStatus = AppStatus.UPDATE
            }
            MoEHelper.getInstance(applicationContext).setAppStatus(appStatus)
            preferences.edit().putBoolean("has_sent_install", true).apply()
            preferences.edit().putBoolean("existing", true).apply()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        androidInjector = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    protected open fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}