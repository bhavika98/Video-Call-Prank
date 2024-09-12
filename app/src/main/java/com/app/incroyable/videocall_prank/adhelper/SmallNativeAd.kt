package com.app.incroyable.videocall_prank.adhelper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.app.incroyable.videocall_prank.adhelper.components.AdLoadState
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNative
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNativeEnable
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.databinding.AdmobSmallNativeBinding

@Composable
fun LoadSmallNative() {
    val context = LocalContext.current
    val placementId = context.getAdString(prefAdmobNative)
    val adLoadState = remember { mutableStateOf(AdLoadState.Loading) }
    val mAdView = remember { mutableStateOf<NativeAdView?>(null) }
    val mNativeAd = remember { mutableStateOf<NativeAd?>(null) }

    if (context.getAdBoolean(prefAdmobNativeEnable) && placementId.isNotEmpty() && context.isOnline()) {
        AndroidViewBinding(factory = AdmobSmallNativeBinding::inflate) {
            this.container.shimmer.isVisible = adLoadState.value == AdLoadState.Loading
            mAdView.value = root.also { adView ->
                adView.bodyView = this.adBody
                adView.callToActionView = this.adCallToAction
                adView.headlineView = this.adHeadline
                adView.iconView = this.adAppIcon
                adView.starRatingView = this.adStars
            }
            mNativeAd.value?.let { nativeAd ->

                this.adHeadline.isVisible = nativeAd.headline?.isNotEmpty() == true
                this.adHeadline.text = nativeAd.headline

                this.adAdvertiser.isVisible = nativeAd.advertiser?.isNotEmpty() == true
                this.adAdvertiser.text = nativeAd.advertiser

                this.adBody.isVisible = nativeAd.body?.isNotEmpty() == true
                this.adBody.text = nativeAd.body

                this.adCallToAction.isVisible = nativeAd.callToAction?.isNotEmpty() == true
                this.adCallToAction.text = nativeAd.callToAction

                this.adStars.isVisible = nativeAd.starRating != null
                this.adStars.rating = nativeAd.starRating?.toFloat() ?: 0.0f

                this.adAppIcon.isVisible = nativeAd.icon != null
                this.adAppIcon.setImageDrawable(nativeAd.icon?.drawable)
            }
        }

        val adLoader = remember {
            AdLoader.Builder(context, placementId)
                .forNativeAd { nativeAd ->
                    mNativeAd.value = nativeAd
                    mAdView.value!!.setNativeAd(nativeAd)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        adLoadState.value = AdLoadState.Loaded
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        super.onAdFailedToLoad(error)
                        adLoadState.value = AdLoadState.Error
                    }
                }).withNativeAdOptions(
                    NativeAdOptions.Builder().setAdChoicesPlacement(
                        NativeAdOptions.ADCHOICES_TOP_RIGHT
                    ).build()
                )
                .build()
        }

        DisposableEffect(Unit) {
            val adRequest = AdRequest.Builder().build()
            adLoader.loadAd(adRequest)

            onDispose {
                if (mAdView.value != null)
                    mAdView.value!!.destroy()
            }
        }
    }
}