package com.app.incroyable.videocall_prank.presentation.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun TitleText(
    title: String
) {
    Text(
        text = title,
        fontFamily = fontVarela,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = getTheme(LocalDarkTheme.current, customTextTitle)
    )
}