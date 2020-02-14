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
        hideStatusBar()
        setContentView(R.layout.activity_tracing)
        hideNavigationBar()
        init()
    }

    private fun init() {
        drawing_view.backgroundResource = R.drawable.a_tracing_bg





    }
}
