package com.app.incroyable.videocall_prank.presentation.wallpaper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.core.storage.fetchMemberData
import com.app.incroyable.videocall_prank.core.storage.wallpaperTitle
import com.app.incroyable.videocall_prank.presentation.common.CustomToolbar
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.presentation.wallpaper.components.WallpaperBody
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun WallpaperScreen(
    userId: Int,
    darkTheme: Boolean
) {
    val context = LocalContext.current
    val darkThemeState = remember { mutableStateOf(darkTheme) }
    CompositionLocalProvider(LocalDarkTheme provides darkThemeState.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(getTheme(LocalDarkTheme.current, customBackground))
        ) {
            CustomToolbar(
                darkTheme = darkThemeState.value,
                wallpaperTitle[userId]
            )
            Spacer(modifier = Modifier.height(4.dp))
            WallpaperBody(
                fetchMemberData(context, userId),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}




