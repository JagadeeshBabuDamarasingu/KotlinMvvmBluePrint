package com.example.kotlinmvvmblueprint.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseFragment<out VB : ViewDataBinding, out VM : AndroidViewModel> :
    DaggerFragment() {


    private lateinit var mViewDataBinding: VB
    private lateinit var mAndroidViewModel: VM
    private lateinit var mActivity: BaseActivity<*, *>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModel(): VM

    abstract fun getBindingVariable(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity = context
            mActivity.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mAndroidViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun getBaseActivity() = mActivity

    override fun onDetach() {
        //todo implement mActivity = null
        super.onDetach()
    }


    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }


}