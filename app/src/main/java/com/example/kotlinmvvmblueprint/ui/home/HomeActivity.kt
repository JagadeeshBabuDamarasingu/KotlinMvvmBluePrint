package com.example.kotlinmvvmblueprint.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlinmvvmblueprint.BR
import com.example.kotlinmvvmblueprint.Category
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.base.BaseActivity
import com.example.kotlinmvvmblueprint.databinding.ActivityHomeBinding
import com.example.kotlinmvvmblueprint.ui.holders.CategoryHolder
import com.example.kotlinmvvmblueprint.ui.video.VideoPlayerActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.progress_circular
import kotlinx.android.synthetic.main.activity_video_player.*
import timber.log.Timber
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeScreenNavigator,
    CategoryHolder.CategoryHolderListener {

    @Inject
    lateinit var mFactory: ViewModelProviderFactory<HomeViewModel>

    @Inject
    lateinit var mAdapter: CategoryAdapter

    @Inject
    lateinit var mLayoutManager: GridLayoutManager

    private val mViewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, mFactory).get(HomeViewModel::class.java)
    }

    override fun getLayoutRes(): Int = R.layout.activity_home

    override fun getViewModel() = mViewModel

    override fun getBindingVariable(): Int = BR.homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mViewModel.setNavigator(this)
        init()
        subscribeToLiveUpdates()
    }

    private fun subscribeToLiveUpdates() {
        mViewModel.categoriesLiveData.observe(this, Observer {
            mAdapter.addCategories(it)
        })
        mViewModel.getLoading().observe(this, Observer {
            progress_circular.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun init() {
        categories_rv.layoutManager = mLayoutManager
        categories_rv.adapter = mAdapter
    }

    override fun onCategoryClicked(category: Category) {
        Timber.e("category clicked %s", category)
        val videoIntent = Intent(this, VideoPlayerActivity::class.java)
        videoIntent.putExtra(VideoPlayerActivity.KEY_CATEGORY, category.name)
        startActivity(videoIntent)
    }
}
