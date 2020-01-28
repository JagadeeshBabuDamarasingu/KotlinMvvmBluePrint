package com.example.kotlinmvvmblueprint.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvmblueprint.R
import com.example.kotlinmvvmblueprint.hideNavigationBar
import com.example.kotlinmvvmblueprint.hideStatusBar
import com.example.kotlinmvvmblueprint.setTransitionsNull
import kotlinx.android.synthetic.main.activity_request_otp.*
import timber.log.Timber

/**
 *  This activity takes phone number and passes to [ValidateOtpActivity]
 */
class RequestOtpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_request_otp)
        hideNavigationBar()
        setTransitionsNull()
        init()
    }

    private fun init() {
        go_btn.setOnClickListener {
            var phoneNumber = phone_edit_text.text.toString()
                .replace("\\s+".toRegex(), "")
                .trim()

            if (phoneNumber.isBlank()) {
                phone_layout.setError(getString(R.string.phone_number_empty), true)
                return@setOnClickListener
            } else if (phoneNumber.length < 10) {
                phone_layout.setError(getString(R.string.phone_number_short), true)
                return@setOnClickListener
            }
            startActivity(Intent(this, ValidateOtpActivity::class.java)
                .apply {
                    phoneNumber = getString(R.string.india_phone_prefix) + phoneNumber
                    Timber.e("phone number is %s", phoneNumber)
                    putExtra(
                        ValidateOtpActivity.KEY_MOBILE, phoneNumber
                    )
                })
        }
    }

}
