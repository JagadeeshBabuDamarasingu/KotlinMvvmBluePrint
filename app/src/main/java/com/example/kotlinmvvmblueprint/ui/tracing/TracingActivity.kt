package com.example.kotlinmvvmblueprint.ui.tracing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.hideNavigationBar
import com.example.kotlinmvvmblueprint.hideStatusBar
import kotlinx.android.synthetic.main.activity_tracing.*
import org.jetbrains.anko.backgroundResource

class TracingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracing)
        init()
    }

    override fun onResume() {
        hideStatusBar()
        hideNavigationBar()
        super.onResume()
    }

    private fun init() {
        drawing_view.backgroundResource = R.drawable.a_tracing_bg





    }
}
