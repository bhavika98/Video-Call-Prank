package com.app.incroyable.videocall_prank.presentation.screen.layout4

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
fun Screen4EndPreview() {
    Screen4End(onEndVideoClick = { })
}

@Composable
fun Screen4End(onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(30)
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .padding(end = 30.dp)
                    .align(Alignment.End)
                    .background(Color.White)
            ) {
                CameraPreview(0)
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ft_box),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_effects, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_effects_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_mute, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_mute_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_flip, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_flip_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_end, 45.dp) {
                            onEndVideoClick()
                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_end_txt, 60.dp)
                    }
                }
            }
        }
    }
}