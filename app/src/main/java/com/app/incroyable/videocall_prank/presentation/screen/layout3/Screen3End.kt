package com.app.incroyable.videocall_prank.presentation.screen.layout3

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CameraPreview
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin

@Preview(device = "id:pixel")
@Composable
fun Screen3EndPreview() {
    Screen3End(onEndVideoClick = { })
}

@Composable
fun Screen3End(onEndVideoClick: () -> Unit) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AdjustableImage(image = R.drawable.fm_left_arrow, width = 20.dp)
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(160.dp)
                        .background(Color.White)
                ) {
                    CameraPreview(0)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AdjustableImage(image = R.drawable.fm_effect, width = 30.dp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            AdjustableImage(R.drawable.fm_swipe, 100.dp)
            VerticalMargin(5)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.fm_box),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CircleImage(R.drawable.fm_video, 55.dp) {}
                    CircleImage(R.drawable.fm_flip, 55.dp) {}
                    CircleImage(R.drawable.fm_mute, 55.dp) {}
                    CircleImage(R.drawable.fm_end, 55.dp) {
                        onEndVideoClick()
                    }
                }
            }
        }
    }
}