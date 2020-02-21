package com.example.kotlinmvvmblueprint.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.kotlinmvvmblueprint.*
import com.example.kotlinmvvmblueprint.ui.holders.VideoThumbHolder
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_video_player.*
import javax.inject.Inject

class VideoPlayerActivity : AppCompatActivity(),
    VideoThumbHolder.VideoHolderListener {

    @Inject
    lateinit var mFactory: ViewModelProviderFactory<VideoPlayerScreenViewModel>

    @Inject
    lateinit var mAdapter: VideoCarouselAdapter

    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition = 0L
    private var isFullScreen = false
    private lateinit var constraintSetMain: ConstraintSet
    private lateinit var constraintSetFull: ConstraintSet


    private val mViewModel: VideoPlayerScreenViewModel by lazy {
        ViewModelProviders.of(
            this, mFactory
        ).get(VideoPlayerScreenViewModel::class.java)
    }

    companion object {
        const val KEY_CATEGORY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        hideStatusBar()
        setContentView(R.layout.activity_video_player)
        hideNavigationBar()
        setUp()
        initObservers()
        processIntent()
    }

    private fun processIntent() {
        mViewModel.getVideos(intent.getStringExtra(KEY_CATEGORY) ?: "NULL")
    }

    private fun initObservers() {
        mViewModel.getVideosLiveData().observe(this, Observer {
            loadVideo(it.first())
            mAdapter.addVideos(it)
        })
        mViewModel.getLoading().observe(this, Observer {
            progress_circular.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setUp() {
        carousel_rv.adapter = mAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        carousel_rv.layoutManager = linearLayoutManager
        back_btn.setOnClickListener { finish() }

        constraintSetMain = ConstraintSet().apply {
            clone(root_layout)
        }
        constraintSetFull = ConstraintSet().apply {
            clone(this@VideoPlayerActivity, R.layout.activity_video_player_full_screen)
        }

        val toggleListener = {
            // uses constraint set to toggle full screen

            val autoTransition = AutoTransition()
                .apply { duration = 250 }
            TransitionManager.beginDelayedTransition(root_layout, autoTransition)
            if (isFullScreen) {
                constraintSetMain.applyTo(root_layout)
            } else {
                constraintSetFull.applyTo(root_layout)
            }
            isFullScreen = !isFullScreen
        }

        player_view.findViewById<AppCompatImageView>(R.id.exo_fullscreen_icon)
            .setOnClickListener{toggleListener()}

    }

    override fun onCategoryClicked(video: Video) = loadVideo(video)

    override fun onStart() {
        super.onStart()
        initializeExoPlayer()
    }

    override fun onStop() {
        super.onStop()
        releaseExoPlayer()
    }

    private fun loadVideo(video: Video) {
        val uri = Uri.parse(video.videoUrl)
        val mediaSource = buildMediaSource(uri)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun initializeExoPlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
        player_view.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultHttpDataSourceFactory("ua", DefaultBandwidthMeter())
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    /**
     *  releases exoPlayer so other apps can use system resources
     */
    private fun releaseExoPlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }
}
