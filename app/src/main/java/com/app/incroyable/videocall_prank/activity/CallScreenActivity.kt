package com.app.incroyable.videocall_prank.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.app.incroyable.videocall_prank.adhelper.loadInterstitialAd
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_VIBRATE
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.CURRENT_CALL
import com.app.incroyable.videocall_prank.presentation.screen.VideoCallScreen


class CallScreenActivity : ComponentActivity() {

    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var powerManager: PowerManager
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar(this@CallScreenActivity)
        initAds()

        powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "andro_apps:wakelock")
        wakeLock.setReferenceCounted(false)

        val userData = intent.getSerializableExtra(CURRENT_CALL) as User
        setContent {
            VideoCallScreen(userData = userData)
        }
    }

    private fun initAds() {
        loadInterstitialAd()
    }

    fun vibratePattern() {
        val isVibrate = getDefaultPreferences(KEY_CALL_VIBRATE) as Boolean
        if (isVibrate) {
            vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect =
                    VibrationEffect.createWaveform(longArrayOf(1000, 1000, 1000, 1000, 1000), 0)
                vibrator.vibrate(vibrationEffect)
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(longArrayOf(1000, 1000, 1000, 1000, 1000), 0)
            }
        }
    }

    @SuppressLint("WakelockTimeout")
    fun pickCall() {
        if (::vibrator.isInitialized) {
            vibrator.cancel()
        }
        wakeLock.acquire()
    }

    fun endCall() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        this.showInterstitialAd()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::vibrator.isInitialized) {
            vibrator.cancel()
        }
        wakeLock.release()
    }
}

private fun hideStatusBar(activity: Activity) {
    val window = activity.window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}