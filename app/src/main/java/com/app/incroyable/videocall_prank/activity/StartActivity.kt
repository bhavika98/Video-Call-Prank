package com.app.incroyable.videocall_prank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.incroyable.videocall_prank.CustomApplication
import com.app.incroyable.videocall_prank.adhelper.loadInterstitialAd
import com.app.incroyable.videocall_prank.presentation.start.StartScreen

class StartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAds()
        setContent {
            StartScreen()
        }
    }

    private fun initAds() {
        loadInterstitialAd()
        val application = application as CustomApplication
        application.initAppOpenAds(application)
    }
}