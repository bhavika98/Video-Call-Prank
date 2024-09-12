package com.app.incroyable.videocall_prank.adhelper

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobAppOpen
import com.app.incroyable.videocall_prank.core.storage.prefAdmobAppOpenEnable
import com.app.incroyable.videocall_prank.core.utils.isOnline

fun Activity.loadAdmobAppOpen(adLoaded: () -> Unit = {}) {
    val placementId = getAdString(prefAdmobAppOpen)
    if (getAdBoolean(prefAdmobAppOpenEnable) && placementId.isNotEmpty() && isOnline()) {
        AppOpenAd.load(
            this,
            placementId,
            AdRequest.Builder().build(),
            1,
            object : AppOpenAdLoadCallback() {
                override fun onAdLoaded(appOpenAd: AppOpenAd) {
                    super.onAdLoaded(appOpenAd)
                    appOpenAd.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdShowedFullScreenContent() {}
                        override fun onAdDismissedFullScreenContent() {
                            adLoaded()
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            adLoaded()
                        }
                    }
                    appOpenAd.show(this@loadAdmobAppOpen)
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    adLoaded()
                }
            })
    } else adLoaded()
}