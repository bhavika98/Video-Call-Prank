package com.app.incroyable.videocall_prank.presentation.screen.layout7

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CameraPreview
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser
import kotlinx.coroutines.delay

@Preview(device = "id:pixel")
@Composable
fun Screen7EndPreview() {
    Screen7End(userData = testUser(), onEndVideoClick = { })
}

@Composable
fun Screen7End(userData: User, onEndVideoClick: () -> Unit) {
    var elapsedTime by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            elapsedTime++
            delay(1000)
        }
    }

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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.sp_chatting, 45.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = userData.name,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentWidth()
                    )
                    ContactName(formatTime(elapsedTime), 12, 0xFFBCBCBC)
                }
            }
            VerticalMargin(10)
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(150.dp)
                        .background(Color.White)
                ) {
                    CameraPreview(0)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AdjustableImage(image = R.drawable.sp_rotate, width = 30.dp)
                    }
                }
                HorizontalMargin(value = 15)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.sp_heart, 40.dp)
                HorizontalMargin(value = 20)
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleImage(R.drawable.sp_audio_btn, 45.dp) {}
                    CircleImage(R.drawable.sp_video_btn, 45.dp) {}
                    CircleImage(R.drawable.sp_end, 45.dp) {
                        onEndVideoClick()
                    }
                }
                HorizontalMargin(value = 20)
                AdjustableImage(R.drawable.sp_menu, 35.dp)
                HorizontalMargin(value = 10)
            }
            VerticalMargin(40)
        }
    }
}

private fun formatTime(timeInSeconds: Long): String {
    val hours = timeInSeconds / 3600
    val minutes = (timeInSeconds % 3600) / 60
    val seconds = timeInSeconds % 60

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}