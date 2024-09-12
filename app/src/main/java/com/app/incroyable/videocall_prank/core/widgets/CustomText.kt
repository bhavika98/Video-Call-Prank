package com.app.incroyable.videocall_prank.core.widgets

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ContactName(
    text: String,
    size: Int,
    color: Long
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = size.sp,
        color = Color(color),
        modifier = Modifier.wrapContentWidth()
    )
}