package com.example.kotlinmvvmblueprint.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmvvmblueprint.data.DataRepository
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(private val dataRepository: DataRepository) : ViewModel() {

    private lateinit var mNavigator: WeakReference<N>
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var error: MutableLiveData<Throwable> = MutableLiveData()


    fun getDataRepository(): DataRepository = dataRepository

    fun getCompositeDisposable(): CompositeDisposable = mCompositeDisposable

    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }

    fun getLoading(): LiveData<Boolean> = isLoading

    fun getError(): LiveData<Throwable> = error

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun setError(throwable: Throwable) {
        this.error.value = throwable
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    fun getNavigator(): N? = mNavigator.get()

}