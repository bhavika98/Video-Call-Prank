package com.app.incroyable.videocall_prank.presentation.setcall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.app.incroyable.videocall_prank.adhelper.LoadMediumNative
import com.app.incroyable.videocall_prank.adhelper.LoadSmallNative
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.presentation.common.CustomToolbar
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.presentation.setcall.components.SetCallBody
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun SetCallScreen(user: User, darkTheme: Boolean) {
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
                stringResource(R.string.set_call)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxSize()) {
                SetCallBody(
                    user = user,
                    darkTheme = darkTheme
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            LoadSmallNative()
        }
    }
}




