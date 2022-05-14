package com.currency.weatherapp.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.currency.weatherapp.MainActivity
import com.currency.weatherapp.common.base.BaseActivity
import com.currency.weatherapp.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val runnable by lazy {
        Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun initOnCreate() {
        handler.postDelayed(runnable, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()

    }
    override fun getViewBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

}