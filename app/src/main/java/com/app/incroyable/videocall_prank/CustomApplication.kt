package com.app.incroyable.videocall_prank

import android.app.Application
import com.applovin.sdk.AppLovinSdk
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobAppOpen
import com.app.incroyable.videocall_prank.core.storage.prefAdmobAppOpenEnable

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Admob
        MobileAds.initialize(
            this
        ) {}

        //Facebook
        AudienceNetworkAds.initialize(this)
        AudienceNetworkInitializeHelper().initialize(this)

        //Applovin
        AppLovinSdk.getInstance(this).mediationProvider = "max"
        AppLovinSdk.initializeSdk(this)
        AdSettings.setDataProcessingOptions(arrayOf())

        //Downloader
        val config: PRDownloaderConfig = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build()
        PRDownloader.initialize(this, config)
    }

    fun initAppOpenAds(
        application: CustomApplication
    ) {
        val placementId = getAdString(prefAdmobAppOpen)
        if (getAdBoolean(prefAdmobAppOpenEnable) && placementId.isNotEmpty()) {
            AppOpenManager(application, placementId)
        }
    }
}