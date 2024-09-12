package com.app.incroyable.videocall_prank.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.text.TextUtils
import androidx.compose.runtime.Composable
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.dialog.NetworkDialog
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefPrivacy
import com.app.incroyable.videocall_prank.core.storage.prefPub

private var lastClickTime: Long = 0

fun singleClick(action: () -> Unit) {
    if (SystemClock.elapsedRealtime() - lastClickTime < 1500L) {
        return
    }
    lastClickTime = SystemClock.elapsedRealtime()
    action.invoke()
}

fun Context.isOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork?.let {
            connectivityManager.getNetworkCapabilities(it)
        }
        networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

@Composable
fun Context.NetworkError(
    onNetworkAvailable: () -> Unit,
    negativeClickListener: () -> Unit
) {
    if (!isOnline()) {
        NetworkDialog(onNetworkAvailable, negativeClickListener)
    } else {
        onNetworkAvailable()
    }
}

fun Context.currentVersion(): Int {
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        packageInfo.longVersionCode.toInt()
    } else {
        packageInfo.versionCode
    }
}

fun Context.policyLink() {
    val privacyLink = getAdString(prefPrivacy)
    if (privacyLink.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyLink))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
fun Context.redirectApp(packageVal: String) {
    val playStoreNotAvailableMsg = getString(R.string.play_store_not_available)
    val turnOnConnectionMsg = getString(R.string.turn_on_connection)
    try {
        val uri = Uri.parse("market://details?id=$packageVal")
        val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
        myAppLinkToMarket.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (isOnline()) {
            startActivitySafe(myAppLinkToMarket) {
                toastMsg(playStoreNotAvailableMsg)
            }
        } else {
            toastMsg(turnOnConnectionMsg)
        }
    } catch (e: ActivityNotFoundException) {
        toastMsg(playStoreNotAvailableMsg)
    }
}

fun Context.startActivitySafe(intent: Intent, onError: () -> Unit = {}) {
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        onError()
    }
}

fun Context.shareApp() {
    val appPackageName = packageName
    val appPlayStoreLink = "https://play.google.com/store/apps/details?id=$appPackageName"
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, "Check out this cool app: $appPlayStoreLink")
    startActivity(Intent.createChooser(intent, "Share via"))
}

fun Context.moreApp() {
    val publication = getAdString(prefPub)
    if (!TextUtils.isEmpty(publication) && !publication.equals("null", ignoreCase = true)) {
        val marketUri = Uri.parse("market://search?q=pub:$publication")
        val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
        if (marketIntent.resolveActivity(packageManager) != null) {
            startActivity(marketIntent)
        } else {
            toastMsg(getString(R.string.play_store_not_available))
        }
    }
}