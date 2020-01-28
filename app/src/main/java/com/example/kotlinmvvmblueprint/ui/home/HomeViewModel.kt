package com.example.kotlinmvvmblueprint.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.kotlinmvvmblueprint.Category
import com.example.kotlinmvvmblueprint.base.BaseViewModel
import com.example.kotlinmvvmblueprint.data.DataRepository
import com.example.kotlinmvvmblueprint.data.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel(dataRepository: DataRepository, apiService: ApiService) :
    BaseViewModel<HomeScreenNavigator>(dataRepository, apiService) {
    val categoriesLiveData: MutableLiveData<List<Category>> = MutableLiveData()

    init {
        getCompositeDisposable().add(
            getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setIsLoading(true) }
                .doFinally { setIsLoading(false) }
                .subscribe { response, throwable ->
                    if (throwable != null) {
                        Timber.e(throwable, "error while fetching categories")
                    } else {
                        categoriesLiveData.value = response.response.videoCategories.values
                            .toList()
                    }
                }
        );

    }


}