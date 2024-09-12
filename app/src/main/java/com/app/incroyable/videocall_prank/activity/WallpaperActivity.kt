package com.app.incroyable.videocall_prank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.app.incroyable.videocall_prank.core.storage.KEY_THEME
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.database.network.USER_ID
import com.app.incroyable.videocall_prank.presentation.wallpaper.WallpaperScreen
import com.app.incroyable.videocall_prank.theme.ThemeSwitcherTheme

class WallpaperActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = intent?.getIntExtra(USER_ID, 0) as Int
        setContent {
            val darkTheme = remember { mutableStateOf(getDefaultPreferences(KEY_THEME) as Boolean) }
            ThemeSwitcherTheme(darkTheme = darkTheme.value) {
                WallpaperScreen(
                    userId = userId,
                    darkTheme = darkTheme.value
                )
            }
        }
    }
}