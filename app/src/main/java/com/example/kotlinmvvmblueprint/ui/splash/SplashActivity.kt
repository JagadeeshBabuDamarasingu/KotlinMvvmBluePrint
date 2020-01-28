package com.example.kotlinmvvmblueprint.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
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
        Handler().postDelayed({
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser == null) {
                openLoginActivity()
            } else {
                openHomeActivity()
            }
        }, 3000)
    }

    private fun openLoginActivity() {
        startActivity(
            Intent(this, RequestOtpActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }, ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, Pair(
                    app_title,
                    getString(R.string.transition_title)
                )
            ).toBundle()
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
