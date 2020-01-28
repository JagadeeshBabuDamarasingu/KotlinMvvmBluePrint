package com.example.kotlinmvvmblueprint


import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *  hides the status bar
 */
fun AppCompatActivity.hideStatusBar() {
    window.decorView.systemUiVisibility =
        window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_FULLSCREEN
    supportActionBar?.hide()
}

/**
 *  hides the Soft Navigation bar
 *  navigation bar can be brought back by swiping up from bottom
 */
fun AppCompatActivity.hideNavigationBar() {
    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
            if (Build.VERSION.SDK_INT in 11..19) {
                View.GONE
            } else {
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
}

fun AppCompatActivity.setTransitionsNull() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.enterTransition = null
        window.exitTransition = null
    }
}


/**
 *  a utility method to disable and dim a view
 */
fun View.setViewEnableStatus(shouldEnable: Boolean) {
    this.alpha = if (shouldEnable) 1f else .88f
    this.isEnabled = shouldEnable
}