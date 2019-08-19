package com.example.kotlinmvvmblueprint.ui.home

import android.os.Bundle
import com.example.kotlinmvvmblueprint.BR
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.base.BaseActivity
import com.example.kotlinmvvmblueprint.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {


    private lateinit var mViewModel: HomeViewModel
    private var mBinding: ActivityHomeBinding? = null


    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun getViewModel(): HomeViewModel {
        return mViewModel
    }

    override fun getBindingVariable(): Int = BR.homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        mBinding?.homeViewModel = mViewModel
        setUp()
        subscribeToLiveUpdates()
    }

    private fun subscribeToLiveUpdates() {

    }

    private fun setUp() {

    }
}
