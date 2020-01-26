package com.example.kotlinmvvmblueprint.di.builder

import com.example.kotlinmvvmblueprint.ui.home.HomeActivity
import com.example.kotlinmvvmblueprint.ui.home.HomeScreeModule
import com.example.kotlinmvvmblueprint.ui.video.VideoPlayerActivity
import com.example.kotlinmvvmblueprint.ui.video.VideoPlayerActivityModule
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


    @ContributesAndroidInjector(
        modules = [VideoPlayerActivityModule::class]
    )
    abstract fun bindVideoPlayerActivity(): VideoPlayerActivity


}