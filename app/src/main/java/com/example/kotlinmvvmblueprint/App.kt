package com.example.kotlinmvvmblueprint

import android.content.Context
import com.example.kotlinmvvmblueprint.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

open class App : DaggerApplication() {

    lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector


    override fun onCreate() {
        super.onCreate()
        initTimber()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        androidInjector = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    protected open fun initTimber() = Timber.plant()

}