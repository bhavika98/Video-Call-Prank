package com.app.incroyable.videocall_prank.adhelper

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.applovin.mediation.MaxAdFormat
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdkUtils
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.app.incroyable.videocall_prank.adhelper.components.AdLoadState
import com.app.incroyable.videocall_prank.adhelper.components.ShimmerLayout
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobBanner
import com.app.incroyable.videocall_prank.core.storage.prefAdmobBannerEnable
import com.app.incroyable.videocall_prank.core.storage.prefApplovinBanner
import com.app.incroyable.videocall_prank.core.storage.prefApplovinBannerEnable
import com.app.incroyable.videocall_prank.core.storage.prefBannerBottom
import com.app.incroyable.videocall_prank.core.storage.prefBannerTop
import com.app.incroyable.videocall_prank.core.utils.isOnline

enum class BannerEnum(val value: String) {
    ADMOB("admob"),
    APPLOVIN("applovin");

    companion object {
        fun fromString(value: String): BannerEnum? {
            return values().find { it.value == value }
        }
    }
}

val heightSpace = 4.dp
val heightShimmer = 60.dp

@Composable
fun TopBanner() {
    val adType = LocalContext.current.getAdString(prefBannerTop)
    Box {
        Column {
            LoadBanner(adType)
            if (getBannerId(adType))
                Spacer(
                    modifier = Modifier
                        .height(heightSpace)
                        .fillMaxWidth()
                )
        }
    }
}

@Composable
fun BottomBanner() {
    val adType = LocalContext.current.getAdString(prefBannerBottom)
    Box {
        Column {
            if (getBannerId(adType))
                Spacer(
                    modifier = Modifier
                        .height(heightSpace)
                        .fillMaxWidth()
                )
            LoadBanner(adType)
        }
    }
}

@Composable
private fun LoadBanner(value: String) {
    when (BannerEnum.fromString(value)) {
        BannerEnum.ADMOB -> {
            AdMobBanner()
        }

        BannerEnum.APPLOVIN -> {
            ApplovinBanner()
        }

        else -> {

        }
    }
}

@Composable
private fun getBannerId(value: String): Boolean {
    val context = LocalContext.current

    return when (BannerEnum.fromString(value)) {
        BannerEnum.ADMOB -> context.getAdBoolean(prefAdmobBannerEnable)
                && context.getAdString(prefAdmobBanner).isNotEmpty()
                && context.isOnline()

        BannerEnum.APPLOVIN -> context.getAdBoolean(prefApplovinBannerEnable)
                && context.getAdString(prefApplovinBanner).isNotEmpty()
                && context.isOnline()

        else -> false
    }
}

@Composable
fun AdMobBanner() {
    val context = LocalContext.current
    val placementId = context.getAdString(prefAdmobBanner)
    val adLoadState = remember { mutableStateOf(AdLoadState.Loading) }

    if (context.getAdBoolean(prefAdmobBannerEnable) && placementId.isNotEmpty() && context.isOnline()) {
        val adView = remember {
            AdView(context).apply {
                setAdSize(getAdsSize(context))
                adUnitId = placementId
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        adLoadState.value = AdLoadState.Loaded
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        adLoadState.value = AdLoadState.Error
                    }
                }
            }
        }

        DisposableEffect(Unit) {
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)

            onDispose {
                adView.destroy()
            }
        }

        Box {
            if (adLoadState.value == AdLoadState.Loading) {
                ShimmerLayout(heightShimmer)
            } else {
                AndroidView(factory = { adView })
            }
        }
    }
}

private fun getAdsSize(context: Context): AdSize {
    val display: Display = (context as Activity).windowManager.defaultDisplay
    val outMetrics = DisplayMetrics()
    display.getMetrics(outMetrics)
    val widthPixels = outMetrics.widthPixels.toFloat()
    val density = outMetrics.density
    val adWidth = (widthPixels / density).toInt()
    return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
}

@Composable
fun ApplovinBanner() {
    val context = LocalContext.current
    val placementId = context.getAdString(prefApplovinBanner)
    val adLoadState = remember { mutableStateOf(AdLoadState.Loading) }

    if (context.getAdBoolean(prefApplovinBannerEnable) && placementId.isNotEmpty() && context.isOnline()) {
        val maxAdView = remember {
            MaxAdView(placementId, context)
        }
        val heightDp = MaxAdFormat.BANNER.getAdaptiveSize(context as Activity).height
        val heightPx = AppLovinSdkUtils.dpToPx(context, heightDp)

        Box(modifier = Modifier.fillMaxWidth()) {
            if (adLoadState.value == AdLoadState.Loading) {
                ShimmerLayout(heightShimmer)
            } else {
                AndroidView(
                    factory = { maxAdView },
                    update = {
                        it.layoutParams = it.layoutParams.apply {
                            height = heightPx
                            width = ViewGroup.LayoutParams.MATCH_PARENT
                        }
                        it.setBackgroundColor(Color.Transparent.toArgb())
//                        it.setListener(object : MaxAdViewAdListener {
//                            override fun onAdLoaded(ad: MaxAd?) {
//                                adLoadState.value = AdLoadState.Loaded
//                            }
//
//                            override fun onAdDisplayed(ad: MaxAd?) {
//                            }
//
//                            override fun onAdHidden(ad: MaxAd?) {
//                            }
//
//                            override fun onAdClicked(ad: MaxAd?) {
//                            }
//
//                            override fun onAdLoadFailed(ad: String?, error: MaxError?) {
//                                adLoadState.value = AdLoadState.Error
//                            }
//
//                            override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
//                            }
//
//                            override fun onAdExpanded(ad: MaxAd?) {
//                            }
//
//                            override fun onAdCollapsed(ad: MaxAd?) {
//                            }
//                        })
                        it.setRevenueListener { }
                        it.loadAd()
                    }
                )
            }
        }

        DisposableEffect(maxAdView) {
            onDispose {
                maxAdView.setListener(null)
            }
        }
    }
}
