package com.example.kotlinmvvmblueprint.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinmvvmblueprint.BR
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.base.BaseActivity
import com.example.kotlinmvvmblueprint.databinding.ActivityHomeBinding
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    @Inject
    lateinit var mFactory: ViewModelProviderFactory<HomeViewModel>

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, mFactory).get(HomeViewModel::class.java)
    }

    private var mBinding: ActivityHomeBinding? = null

    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun getViewModel(): HomeViewModel {
        return mViewModel
    }

    override fun getBindingVariable(): Int = BR.homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        setUp()
        subscribeToLiveUpdates()
    }

    private fun subscribeToLiveUpdates() {
        Timber.e("subscribeToLiveUpdates")
        mViewModel.getData().observe(this, Observer { list ->
            run {
                Timber.e("list size is %s", list.size)
                for (person in list) {
                    Timber.e("Person is %s", person.toString())
                }
            }
        })

    }

    private fun setUp() {

    }
}
