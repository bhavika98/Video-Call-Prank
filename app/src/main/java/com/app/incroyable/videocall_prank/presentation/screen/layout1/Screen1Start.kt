package com.app.incroyable.videocall_prank.presentation.screen.layout1

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.animation.Screen1Animation
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.ProfileImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen1StartPreview() {
    Screen1Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen1Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(50)
            AdjustableImage(R.drawable.wa_encrypted, 240.dp)
            VerticalMargin(20)
            ProfileImage(userData.image, 95.dp)
            VerticalMargin(10)
            ContactName(userData.name, 20, 0xFFFFFCFF)
            VerticalMargin(2)
            AdjustableImage(R.drawable.wa_video_call, 200.dp)

            Spacer(modifier = Modifier.weight(1f))

            AdjustableImage(R.drawable.wa_scoring, 50.dp)
            VerticalMargin(20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    VerticalMargin(30)
                    CircleImage(R.drawable.wa_end, 55.dp) {
                        onEndVideoClick()
                    }
                }
                Column {
                    Screen1Animation(R.drawable.wa_video,
                        onSwipeUp = {
                            onStartVideoClick()
                        }
                    )
                    VerticalMargin(30)
                }
                Column {
                    VerticalMargin(30)
                    CircleImage(R.drawable.wa_message, 55.dp) {}
                }
            }
            VerticalMargin(20)
            AdjustableImage(R.drawable.wa_swipe, 200.dp)
            VerticalMargin(40)
        }
    }
}