package com.app.incroyable.videocall_prank.presentation.screen.layout2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.animation.Screen2Animation
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.ProfileImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen2StartPreview() {
    Screen2Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen2Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.insta_rectangle),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(50)
            AdjustableImage(R.drawable.insta_video_chat, 250.dp)
            VerticalMargin(20)
            ProfileImage(userData.image, 95.dp)
            VerticalMargin(10)
            ContactName(userData.name, 20, 0xFFFFFCFF)

            Spacer(modifier = Modifier.weight(1f))

            AdjustableImage(R.drawable.insta_swipe_up, 250.dp)
            VerticalMargin(20)
            Screen2Animation(R.drawable.insta_video,
                onSwipeDown = {
                    onEndVideoClick()
                },
                onSwipeUp = {
                    onStartVideoClick()
                }
            )
            VerticalMargin(20)
            AdjustableImage(R.drawable.insta_swipe_down, 250.dp)
            VerticalMargin(80)
        }
    }
}