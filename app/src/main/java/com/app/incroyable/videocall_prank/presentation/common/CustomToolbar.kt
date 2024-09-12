package com.app.incroyable.videocall_prank.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customBottomIcon
import com.app.incroyable.videocall_prank.theme.customPanel
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun CustomToolbar(
    darkTheme: Boolean,
    title: String
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
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
                    end = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(65.dp)
                    .background(getTheme(darkTheme, customPanel))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        context
                            .findActivity()
                            ?.finish()
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customBottomIcon
                        )
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(12.dp),
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TitleText(
                title = title
            )
        }
    }
}



