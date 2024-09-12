package com.mr0xf00.easycrop

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.lerp
import com.mr0xf00.easycrop.utils.lerpAngle

@Composable
internal fun animateImgTransform(target: ImgTransform): State<ImgTransform> {
    var prev by remember { mutableStateOf<ImgTransform?>(null) }
    val current = remember { mutableStateOf(target) }
    LaunchedEffect(target) {
        val a = prev
        try {
            if (a != null) animate(0f, 1f) { p, _ ->
                current.value = (a.lerp(target, p))
            }
        } finally {
            current.value = (target)
            prev = target
        }
    }
    return current
}

private fun ImgTransform.lerp(target: ImgTransform, p: Float): ImgTransform {
    if (p == 0f) return this
    if (p == 1f) return target
    return ImgTransform(
        angleDeg = lerpAngle(angleDeg, target.angleDeg, p),
        scale = lerp(scale, target.scale, p),
        pivotRel = lerp(pivotRel, target.pivotRel, p)
    )
}