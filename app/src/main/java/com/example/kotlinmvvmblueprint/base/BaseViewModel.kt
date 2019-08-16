package com.example.kotlinmvvmblueprint.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    private lateinit var mNavigator: WeakReference<N>




}