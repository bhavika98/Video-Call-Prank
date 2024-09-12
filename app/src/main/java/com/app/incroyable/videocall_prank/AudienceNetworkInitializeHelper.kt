package com.app.incroyable.videocall_prank

import android.content.Context
import android.util.Log
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds

class AudienceNetworkInitializeHelper : AudienceNetworkAds.InitListener {

    companion object {
        private const val DEBUG = true
    }

    fun initialize(context: Context) {
        if (!AudienceNetworkAds.isInitialized(context)) {
            if (DEBUG) {
                AdSettings.turnOnSDKDebugger(context)
            }

            AudienceNetworkAds
                .buildInitSettings(context)
                .withInitListener(this)
                .initialize()
        }
    }

    override fun onInitialized(result: AudienceNetworkAds.InitResult) {
        Log.d(AudienceNetworkAds.TAG, result.message)
    }
}