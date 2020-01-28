package com.example.kotlinmvvmblueprint.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvmblueprint.*
import com.example.kotlinmvvmblueprint.ui.home.HomeActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_validate_otp.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 *  This activity tries to auto retrieve OTP received
 *  if fails, user can manually input OTP
 */
class ValidateOtpActivity : AppCompatActivity() {
    private lateinit var phoneVerificationCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var countDownTimer: CountDownTimer

    companion object {
        const val KEY_MOBILE = "key_mobile"
        const val OTP_READ_TIMEOUT: Long = 60_000// in milli seconds
        const val COUNTDOWN_INTERVAL: Long = 1000
        private var mVerificationId: String = ""
        private lateinit var mForceResendingToken: PhoneAuthProvider.ForceResendingToken

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_validate_otp)
        hideNavigationBar()
        setTransitionsNull()
        init()
    }


    private fun init() {
        otp_view.post {
            /* a work around for a bug in otp view doesn't allows for proper rendering of ui */
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            val unit = width / 19
            otp_view.itemWidth = unit * 2
            otp_view.itemSpacing = unit
            otp_view.itemHeight = unit * 3
        }

        countDownTimer = object : CountDownTimer(OTP_READ_TIMEOUT, COUNTDOWN_INTERVAL) {
            override fun onFinish() {
                resend_btn_timer.visibility = View.GONE
                resend_btn_timer.setViewEnableStatus(true)
            }

            override fun onTick(millisUntilFinished: Long) {
                resend_btn_timer.text =
                    getString(
                        R.string.otp_resend_enable_text,
                        (millisUntilFinished / COUNTDOWN_INTERVAL).toInt()
                    )
            }
        }

        phoneVerificationCallBack =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    // FireBase verified otp for us, so we can proceed saving user and next activity
                    cancelTimer()
                    otp_view.setText(phoneAuthCredential.smsCode)
                    signInWithPhoneAuthCredential(phoneAuthCredential)
                }

                override fun onVerificationFailed(firebaseException: FirebaseException) {
                    cancelTimer()
                    Timber.e(firebaseException, "error while otp")
                }

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, forceResendingToken)
                    mVerificationId = verificationId
                    mForceResendingToken = forceResendingToken
                    cancelTimer()
                    countDownTimer.start()
                    resend_btn_timer.setViewEnableStatus(false)
                }

            }

        validate_btn.setOnClickListener {
            val otp = otp_view.text.toString().trim()
            if (otp.isBlank()) {
                Toast.makeText(this, getString(R.string.enter_otp), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            verifyCode(otp)
        }

        sendCode(intent.getStringExtra(KEY_MOBILE) ?: "")

        resend_btn.setOnClickListener {
            sendCode(intent.getStringExtra(KEY_MOBILE) ?: "")
        }
    }

    fun cancelTimer() {
        countDownTimer.cancel()
        resend_btn_timer.visibility = View.INVISIBLE
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendCode(phoneNumber: String) {
        if (!phoneNumber.isBlank()) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                OTP_READ_TIMEOUT,
                TimeUnit.MILLISECONDS,
                this,
                phoneVerificationCallBack
            )
        }
    }

    /**
     *  signs in a user using [PhoneAuthCredential] and
     *  if successful opens next screen
     *  if fails shows an error toast message
     */
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Timber.e("user is %s", user)
                    openHomeActivity()
                } else {
                    // Sign in failed
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }

    private fun openHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
        finish()
    }
}
