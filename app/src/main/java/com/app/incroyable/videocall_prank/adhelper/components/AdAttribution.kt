package com.app.incroyable.videocall_prank.adhelper.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.theme.adAttributionMoreTextStyle
import com.app.incroyable.videocall_prank.theme.adAttributionNativeTextStyle

@Composable
fun AdAttributionMore() {
    Card(
        modifier = Modifier
            .size(25.dp, 21.dp)
            .padding(start = 7.dp, bottom = 10.dp)
            .border(0.dp, Color.Transparent),
        backgroundColor = Color(0XFFFFCC66),
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AD",
                style = adAttributionMoreTextStyle
            )
        }
    }
}

@Composable
fun AdAttributionNative() {
    Card(
        modifier = Modifier
            .size(26.dp, 17.dp)
            .padding(start = 2.dp, top = 2.dp)
            .border(0.dp, Color.Transparent),
        backgroundColor = Color(0XFFFFCC66),
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AD",
                style = adAttributionNativeTextStyle
            )
        }
    }
}
