package com.example.kotlinmvvmblueprint.ui.video

import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.data.DataRepository
import com.example.kotlinmvvmblueprint.data.network.ApiService
import com.example.kotlinmvvmblueprint.ui.home.VideoCarouselAdapter
import dagger.Module
import dagger.Provides

@Module
class VideoPlayerActivityModule {
    @Provides
    fun providesVideoPlayerViewModel(dataRepository: DataRepository, apiService: ApiService): VideoPlayerScreenViewModel =
        VideoPlayerScreenViewModel(dataRepository,apiService)

    @Provides
    fun providesViewModelProviderFactory(mViewModel: VideoPlayerScreenViewModel): ViewModelProviderFactory<VideoPlayerScreenViewModel> =
        ViewModelProviderFactory(mViewModel)

    @Provides
    fun providesCarouselAdapter(videoPlayerActivity: VideoPlayerActivity) = VideoCarouselAdapter(videoPlayerActivity)


}