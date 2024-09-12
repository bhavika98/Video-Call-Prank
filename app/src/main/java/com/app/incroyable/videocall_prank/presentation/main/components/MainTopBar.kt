package com.app.incroyable.videocall_prank.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.presentation.common.TitleText
import com.app.incroyable.videocall_prank.presentation.common.ThemeSwitcher
import com.app.incroyable.videocall_prank.theme.customPanel
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun MainTopBar(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(getTheme(darkTheme, customPanel))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 8.dp,
                    end = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleText(
                title = stringResource(R.string.hi_army)
            )
            Spacer(modifier = Modifier.weight(1f))
            ThemeSwitcher(
                darkTheme = darkTheme,
                onClick = onThemeUpdated
            )
        }
    }
}

