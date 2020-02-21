package com.example.kotlinmvvmblueprint.ui.social

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.hideNavigationBar
import com.example.kotlinmvvmblueprint.hideStatusBar
import com.example.kotlinmvvmblueprint.ui.social.model.Feed
import com.example.kotlinmvvmblueprint.ui.social.model.avatarList
import com.example.kotlinmvvmblueprint.ui.social.model.generateRandomFeed
import kotlinx.android.synthetic.main.activity_social.*

class SocialActivity : AppCompatActivity() {

    private val feedAdapter = SocialFeedAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)
        init()
    }

    private fun init(){
        feed_rv.layoutManager = LinearLayoutManager(this)
        feed_rv.adapter = feedAdapter
        user_avatar.clipToOutline = true

        Glide.with(this)
            .load(avatarList.random())
            .error(R.drawable.ic_person)
            .apply(getCircleTransform())
            .into(user_avatar)

        val feedList = mutableListOf<Feed>()
        repeat(88){
            feedList.add(generateRandomFeed())
        }
        feedAdapter.addItems(feedList)
    }

    override fun onResume() {
        hideStatusBar()
        hideNavigationBar()
        super.onResume()
    }
}
