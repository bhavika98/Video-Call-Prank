package com.app.incroyable.videocall_prank.presentation.screen.layout7

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen7StartPreview() {
    Screen7Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen7Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.sp_rectangle),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(60)
            ContactName(userData.name, 20, 0xFFFFFCFF)
            VerticalMargin(2)
            AdjustableImage(R.drawable.sp_incoming_call, 200.dp)
            VerticalMargin(30)
            Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.sp_ring),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = "${userData.name[0]}",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color(0XFF00A8FF),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.wrapContentWidth()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.sp_message, 35.dp)
                AdjustableImage(R.drawable.sp_audio, 35.dp)
                HorizontalMargin(value = 10)
            }
            VerticalMargin(50)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.sp_decline, 55.dp) {
                        onEndVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.sp_decline_txt, 55.dp)
                }
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.sp_video, 55.dp) {
                        onStartVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.sp_video_txt, 55.dp)
                }
            }
            VerticalMargin(60)
        }
    }
}