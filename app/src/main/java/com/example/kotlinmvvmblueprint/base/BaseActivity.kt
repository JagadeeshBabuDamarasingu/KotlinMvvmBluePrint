package com.example.kotlinmvvmblueprint.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<out VB : ViewDataBinding, out VM : AndroidViewModel> : DaggerAppCompatActivity(),
    BaseFragment.Callback {

    private lateinit var mViewDataBinding: VB
    private lateinit var mAndroidViewModel: VM

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): VM

    abstract fun getBindingVariable(): Int

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (item?.itemId == android.R.id.home) finish()
//        return super.onOptionsItemSelected(item)
//    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        performDependencyInjection();
        super.onCreate(savedInstanceState, persistentState)
        performDataBinding()

    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        mViewDataBinding.setVariable(getBindingVariable(), mAndroidViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }


}