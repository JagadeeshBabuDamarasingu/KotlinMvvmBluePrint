package com.example.kotlinmvvmblueprint.ui.splash

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.AnyRes
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.hideNavigationBar
import com.example.kotlinmvvmblueprint.hideStatusBar
import com.example.kotlinmvvmblueprint.setTransitionsNull
import com.example.kotlinmvvmblueprint.ui.home.HomeActivity
import com.example.kotlinmvvmblueprint.ui.login.RequestOtpActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_splash)
        hideNavigationBar()
        setTransitionsNull()
        initVideo()
    }

    private fun initVideo() {
        video_view.setVideoURI(getResourceUri(R.raw.splash_video))
        video_view.setZOrderOnTop(true)
        video_view.setOnCompletionListener {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser == null) {
                openLoginActivity()
            } else {
                openHomeActivity()
            }
        }
        video_view.start()
    }

    private fun getResourceUri(@AnyRes resourceId: Int) = Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(packageName)
        .path(resourceId.toString())
        .build()

    private fun openLoginActivity() {
        startActivity(
            Intent(this, RequestOtpActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
        )
        finish()
    }

    private fun openHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
        finish()
    }
}
