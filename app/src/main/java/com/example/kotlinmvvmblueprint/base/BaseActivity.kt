package com.example.kotlinmvvmblueprint.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<out VB : ViewDataBinding, out VM : ViewModel> : DaggerAppCompatActivity(),
    BaseFragment.Callback {


    private var mViewDataBinding: VB? = null
    private var mAndroidViewModel: VM? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): VM

    abstract fun getBindingVariable(): Int

    fun getViewDataBinding(): VB? = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        performDependencyInjection();
        super.onCreate(savedInstanceState, persistentState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        mAndroidViewModel = if (mAndroidViewModel == null) getViewModel() else mAndroidViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mAndroidViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }


}