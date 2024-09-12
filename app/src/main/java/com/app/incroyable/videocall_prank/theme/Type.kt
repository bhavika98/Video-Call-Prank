package com.app.incroyable.videocall_prank.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.R

val fontVarela = FontFamily(Font(R.font.varela))

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontVarela,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)