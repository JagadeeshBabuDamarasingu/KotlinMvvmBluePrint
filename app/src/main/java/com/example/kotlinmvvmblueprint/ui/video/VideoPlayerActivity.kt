package com.example.kotlinmvvmblueprint.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.Video
import com.example.kotlinmvvmblueprint.ViewModelProviderFactory
import com.example.kotlinmvvmblueprint.ui.holders.VideoThumbHolder
import com.example.kotlinmvvmblueprint.ui.home.VideoCarouselAdapter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity(),
    VideoThumbHolder.VideoHolderListener {


    val mAdapter = VideoCarouselAdapter(this)

    private lateinit var simpleExoplayer: SimpleExoPlayer

    private var playbackPosition = 0L

    private val mViewModel: VideoPlayerScreenViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelProviderFactory<VideoPlayerScreenViewModel>(mViewModel)
        ).get(VideoPlayerScreenViewModel::class.java)
    }

    companion object {
        const val KEY_CATEGORY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        setUp()
        initObservers()
        processIntent()
    }

    private fun processIntent() {
        mViewModel.getVideos(intent.getStringExtra(KEY_CATEGORY) ?: "NULL")
    }

    private fun initObservers() {
        mViewModel.getVideosLiveDat().observe(this, Observer {
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
    }

    override fun onCategoryClicked(category: Video) {
        loadVideo(category)
    }

    override fun onStart() {
        super.onStart()
        initializeExoplayer()
    }

    override fun onStop() {
        super.onStop()
        releaseExoplayer()
    }

    private fun loadVideo(video: Video) {
        val uri = Uri.parse(video.videoUrl)
        val mediaSource = buildMediaSource(uri)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun initializeExoplayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(this).build()
        player_view.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultHttpDataSourceFactory("ua", DefaultBandwidthMeter())
        val dashChunkSourceFactory = DefaultDashChunkSource.Factory(dataSourceFactory)
        return DashMediaSource(uri, dataSourceFactory, dashChunkSourceFactory, null, null)
    }

    private fun releaseExoplayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }
}
