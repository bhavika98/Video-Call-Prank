package com.app.incroyable.videocall_prank.core.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun Screen1Animation(
    imageResource: Int,
    onSwipeUp: () -> Unit
) {
    val offsetValue = 50f
    var offsetY by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val translateY = remember { Animatable(0f) }
    var alphaValue by remember { mutableStateOf(1f) }

    LaunchedEffect(isDragging) {
        if (!isDragging) {
            while (true) {
                translateY.animateTo(
                    targetValue = -20f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )

                delay(400L)

                translateY.animateTo(
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            }
        }
    }

    Image(
        modifier = Modifier
            .size(55.dp)
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .graphicsLayer(
                translationY = translateY.value,
                alpha = alphaValue
            )
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    isDragging = true
                    if (offsetY in -offsetValue.dp.toPx()..0.dp.toPx()) {
                        offsetY += dragAmount.y
                        val alphaRange = offsetValue.dp.toPx()
                        alphaValue = 1f - (abs(offsetY) / alphaRange).coerceIn(0f, 1f)
                    } else {
                        if (offsetY <= -offsetValue.dp.toPx()) {
                            onSwipeUp()
                        } else {
                            offsetY = 0f
                            alphaValue = 1f
                        }
                    }
                }
            },
        painter = painterResource(id = imageResource),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}







