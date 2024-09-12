package com.app.incroyable.videocall_prank.core.widgets

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints

@Composable
fun CustomSquareBox(content: @Composable () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
            .layout { measurable, constraints ->
                val width = constraints.maxWidth
                val height = if (width > constraints.maxHeight) {
                    constraints.maxHeight
                } else {
                    width
                }

                val placeable = measurable.measure(
                    Constraints.fixed(width, height)
                )
                layout(width, height) {
                    placeable.place(0, 0)
                }
            }
    ) {
        content()
    }
}

