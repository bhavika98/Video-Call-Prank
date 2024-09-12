package com.app.incroyable.videocall_prank.presentation.theme

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.adhelper.BottomBanner
import com.app.incroyable.videocall_prank.adhelper.TopBanner
import com.app.incroyable.videocall_prank.core.storage.defaultTheme
import com.app.incroyable.videocall_prank.presentation.common.CustomToolbar
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.presentation.theme.components.ThemeBody
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun ThemeScreen(darkTheme: Boolean) {
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
                stringResource(R.string.theme)
            )
            Spacer(modifier = Modifier.height(4.dp))
            TopBanner()
            ThemeBody(
                defaultTheme(),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            BottomBanner()
        }
    }
}




