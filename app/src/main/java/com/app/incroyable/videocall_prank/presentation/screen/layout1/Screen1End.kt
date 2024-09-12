package com.app.incroyable.videocall_prank.presentation.screen.layout1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin

@Preview(device = "id:pixel")
@Composable
fun Screen1EndPreview() {
    Screen1End(onEndVideoClick = { })
}

@Composable
fun Screen1End(onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(30)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AdjustableImage(R.drawable.wa_down, 15.dp)
                AdjustableImage(R.drawable.wa_encrypted, 240.dp)
                AdjustableImage(R.drawable.wa_add, 15.dp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircleImage(R.drawable.wa_cut, 60.dp) {
                    onEndVideoClick()
                }
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(140.dp)
                        .background(Color.White)
                        .align(Alignment.CenterEnd)
                ) {
                    CameraPreview(0)
                }
            }
            VerticalMargin(40)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AdjustableImage(R.drawable.wa_rotate, 19.dp)
                AdjustableImage(R.drawable.wa_video_camera, 19.dp)
                AdjustableImage(R.drawable.wa_microphone, 15.dp)
            }
            VerticalMargin(40)
        }
    }
}