package com.example.kotlinmvvmblueprint.di.builder

import com.example.kotlinmvvmblueprint.ui.home.HomeActivity
import com.example.kotlinmvvmblueprint.ui.home.HomeScreeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            HomeScreeModule::class
        ]
    )
    abstract fun bindHomeActivity(): HomeActivity


}