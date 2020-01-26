package com.example.kotlinmvvmblueprint.ui.home

import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.data.DataRepository
import com.example.kotlinmvvmblueprint.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
class HomeScreeModule {

    @Provides
    fun providesHomeScreenViewModel(
        dataRepository: DataRepository,
        apiService: ApiService
    ): HomeViewModel =
        HomeViewModel(dataRepository, apiService)

    @Provides
    fun providesViewModelProviderFactory(homeViewModel: HomeViewModel): ViewModelProviderFactory<HomeViewModel> =
        ViewModelProviderFactory(homeViewModel)

    @Provides
    fun providesCategoryAdapter(activity: HomeActivity) = CategoryAdapter(activity)

    @Provides
    fun providesLayoutManager(activity: HomeActivity) = GridLayoutManager(activity, 4)

}