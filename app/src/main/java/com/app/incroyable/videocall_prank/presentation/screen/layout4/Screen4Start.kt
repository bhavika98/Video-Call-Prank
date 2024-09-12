package com.app.incroyable.videocall_prank.presentation.screen.layout4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.core.widgets.ProfileImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen4StartPreview() {
    Screen4Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen4Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(80)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp, end = 30.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                )
                {
                    ContactName(userData.name, 20, 0xFFFFFCFF)
                    VerticalMargin(2)
                    ContactName(stringResource(id = R.string.facetime), 11, 0xFFD9D9D9)
                }
                ProfileImage(userData.image, 55.dp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.ft_remind_me, 55.dp)
                AdjustableImage(R.drawable.ft_message, 55.dp)
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
                    CircleImage(R.drawable.ft_decline, 55.dp) {
                        onEndVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.ft_decline_txt, 55.dp)
                }
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.ft_accept, 55.dp) {
                        onStartVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.ft_accept_txt, 55.dp)
                }
            }
            VerticalMargin(80)
        }
    }
}