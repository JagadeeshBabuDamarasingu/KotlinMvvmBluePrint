package com.example.kotlinmvvmblueprint.ui.video

import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmblueprint.Video
import com.example.kotlinmvvmblueprint.base.BaseViewModel
import com.example.kotlinmvvmblueprint.data.DataRepository
import com.example.kotlinmvvmblueprint.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class VideoPlayerScreenViewModel(dataRepository: DataRepository, apiService: ApiService) :
    BaseViewModel<VideoPlayerScreenNavigator>(dataRepository, apiService) {

    val videosLiveData: MutableLiveData<List<Video>> = MutableLiveData()

    fun getVideosLiveDat() = videosLiveData


    fun getVideos(category: String) {
        getCompositeDisposable().add(
            getApiService().getVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setIsLoading(true) }
                .doFinally { setIsLoading(false) }
                .subscribe { response, throwable ->
                    if (throwable != null) {
                        Timber.e(throwable, "error while fetching categories")
                    } else {
                        videosLiveData.value = response.response.videos.values.toList()
                            .filter { it.categoryList.contains(category) }
                    }
                }
        )

    }

}