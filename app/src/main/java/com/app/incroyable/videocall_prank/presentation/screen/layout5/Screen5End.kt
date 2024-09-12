package com.app.incroyable.videocall_prank.presentation.screen.layout5

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CameraPreview
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin

@Preview(device = "id:pixel")
@Composable
fun Screen5EndPreview() {
    Screen5End(onEndVideoClick = { })
}

@Composable
fun Screen5End(onEndVideoClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
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
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.sc_back, 20.dp)
                Image(
                    modifier = Modifier
                        .width(65.dp)
                        .height(35.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onEndVideoClick()
                        },
                    painter = painterResource(id = R.drawable.sc_end),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.sc_speaker, 55.dp)
                AdjustableImage(R.drawable.sc_mute, 55.dp)
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(CircleShape)
                ) {
                    CameraPreview(100)
                }
                AdjustableImage(R.drawable.sc_video, 55.dp)
                AdjustableImage(R.drawable.sc_flip, 55.dp)
            }
            VerticalMargin(30)
        }
    }
}