package com.example.kotlinmvvmblueprint.di.component

import android.app.Application
import com.example.kotlinmvvmblueprint.App
import com.example.kotlinmvvmblueprint.di.builder.ActivityBuilder
import com.example.kotlinmvvmblueprint.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: App)
}
