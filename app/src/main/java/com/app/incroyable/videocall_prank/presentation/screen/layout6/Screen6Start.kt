package com.app.incroyable.videocall_prank.presentation.screen.layout6

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun Screen6StartPreview() {
    Screen6Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen6Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(70)
            ProfileImage(userData.image, 65.dp)
            VerticalMargin(10)
            ContactName(userData.name, 20, 0xFFFFFCFF)
            VerticalMargin(6)
            AdjustableImage(R.drawable.gd_incoming, 200.dp)
            VerticalMargin(10)
            AdjustableImage(R.drawable.gd_padlock, 150.dp)

            Spacer(modifier = Modifier.weight(1f))

            AdjustableImage(R.drawable.gd_swipe_up, 250.dp)
            VerticalMargin(20)
            Screen2Animation(R.drawable.gd_video_call,
                onSwipeDown = {
                    onEndVideoClick()
                },
                onSwipeUp = {
                    onStartVideoClick()
                }
            )
            VerticalMargin(70)
            AdjustableImage(R.drawable.gd_swipe_down, 250.dp)
            VerticalMargin(20)
        }
    }
}