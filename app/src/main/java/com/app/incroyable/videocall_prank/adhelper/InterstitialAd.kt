package com.app.incroyable.videocall_prank.adhelper

import android.app.Activity
import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.app.incroyable.videocall_prank.core.storage.adCounter
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdInt
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.incrementCounter
import com.app.incroyable.videocall_prank.core.storage.prefAdmobInterstitial
import com.app.incroyable.videocall_prank.core.storage.prefAdmobInterstitialEnable
import com.app.incroyable.videocall_prank.core.storage.prefApplovinInterstitial
import com.app.incroyable.videocall_prank.core.storage.prefApplovinInterstitialEnable
import com.app.incroyable.videocall_prank.core.storage.prefFirstAd
import com.app.incroyable.videocall_prank.core.storage.prefFirstAdDefault
import com.app.incroyable.videocall_prank.core.storage.prefSecondAd
import com.app.incroyable.videocall_prank.core.storage.prefSecondAdDefault
import com.app.incroyable.videocall_prank.core.utils.isOnline

private var mInterstitialAdMob: InterstitialAd? = null
private var interstitialAdAL: MaxInterstitialAd? = null

fun Context.loadInterstitialAd() {
    loadAdmobInterstitial()
    loadApplovinInterstitial()
}

fun Context.loadAdmobInterstitial() {
    val placementId = getAdString(prefAdmobInterstitial)
    if (getAdBoolean(prefAdmobInterstitialEnable) && placementId.isNotEmpty() && isOnline()) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            placementId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAdMob = interstitialAd
                    interstitialAd.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                mInterstitialAdMob = null
                                loadAdmobInterstitial()
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                mInterstitialAdMob = null
                            }

                            override fun onAdShowedFullScreenContent() {}
                        }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    mInterstitialAdMob = null
                }
            })
    }
}

fun Context.loadApplovinInterstitial() {
    val placementId = getAdString(prefApplovinInterstitial)
    if (getAdBoolean(prefApplovinInterstitialEnable) && placementId.isNotEmpty() && isOnline()) {
        interstitialAdAL = MaxInterstitialAd(placementId, this as Activity)
        interstitialAdAL!!.setListener(object : MaxAdListener {
            override fun onAdLoaded(ad: MaxAd) {}
            override fun onAdDisplayed(ad: MaxAd) {}
            override fun onAdHidden(ad: MaxAd) {
                if (interstitialAdAL != null) {
                    interstitialAdAL!!.loadAd()
                }
            }

            override fun onAdClicked(ad: MaxAd) {}
            override fun onAdLoadFailed(adUnitId: String, error: MaxError) {}
            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {}
        })
        if (interstitialAdAL != null) {
            interstitialAdAL!!.loadAd()
        }
    }
}

fun Context.showInterstitialAd() {
    val adCounterPref = getAdInt(adCounter, 1).coerceAtLeast(1)
    val firstAd = getAdInt(prefFirstAd, prefFirstAdDefault)
    val secondAd = getAdInt(prefSecondAd, prefSecondAdDefault)
    val getValue = when {
        adCounterPref % firstAd == 0 -> 0
        adCounterPref % secondAd == 0 -> 1
        else -> 2
    }
    when (getValue) {
        0 -> showApplovinInterstitial()
        1 -> showAdmobInterstitial()
    }
    incrementCounter()
}

fun Context.showAdmobInterstitial() {
    if (mInterstitialAdMob != null) mInterstitialAdMob!!.show(this as Activity)
}

fun showApplovinInterstitial() {
    if (interstitialAdAL != null && interstitialAdAL!!.isReady) {
        interstitialAdAL!!.showAd()
    }
}