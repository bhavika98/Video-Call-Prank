package com.app.incroyable.videocall_prank.presentation.screen.layout2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CameraPreview
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin

@Preview(device = "id:pixel")
@Composable
fun Screen2EndPreview() {
    Screen2End(onEndVideoClick = { })
}

@Composable
fun Screen2End(onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
            ) {
                CameraPreview(0)
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(25)
            Row(modifier = Modifier.fillMaxWidth()) {
                HorizontalMargin(value = 25)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircleImage(R.drawable.insta_minimize, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_video_call, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_mute, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_rotate, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_close, 35.dp) {
                        onEndVideoClick()
                    }
                }
                HorizontalMargin(value = 25)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                AdjustableImage(R.drawable.insta_effects, 35.dp)
                HorizontalMargin(value = 25)
                AdjustableImage(R.drawable.insta_media, 35.dp)
                HorizontalMargin(value = 25)
                AdjustableImage(R.drawable.insta_add, 35.dp)
                HorizontalMargin(value = 25)
            }
            VerticalMargin(25)
        }
    }
}