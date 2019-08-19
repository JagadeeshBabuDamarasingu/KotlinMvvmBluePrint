package com.example.kotlinmvvmblueprint.ui.home

import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.data.DataRepository
import dagger.Module
import dagger.Provides

@Module
class HomeScreeModule {

    @Provides
    fun providesHomeScreenViewModel(dataRepository: DataRepository): HomeViewModel = HomeViewModel(dataRepository)

    @Provides
    fun providesViewModelProviderFactory(homeViewModel: HomeViewModel) =
        ViewModelProviderFactory<HomeViewModel>(homeViewModel)

}