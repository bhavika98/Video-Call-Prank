package com.app.incroyable.videocall_prank.presentation.screen.layout6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
fun Screen6EndPreview() {
    Screen6End(onEndVideoClick = { })
}

@Composable
fun Screen6End(onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            ) {
                AdjustableImage(R.drawable.gd_duo, 115.dp)
                HorizontalMargin(value = 10)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            ) {
                HorizontalMargin(value = 10)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    CameraPreview(0)
                }
            }
            VerticalMargin(30)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.gd_video, 55.dp)
                AdjustableImage(R.drawable.gd_mute, 55.dp)
                CircleImage(R.drawable.gd_end, 65.dp) {
                    onEndVideoClick()
                }
                AdjustableImage(R.drawable.gd_flip, 55.dp)
                AdjustableImage(R.drawable.gd_filter, 55.dp)
            }
            VerticalMargin(30)
        }
    }
}