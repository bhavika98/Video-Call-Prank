package com.app.incroyable.videocall_prank.presentation.screen.layout5

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.ProfileImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen5StartPreview() {
    Screen5Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen5Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp)
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ProfileImage(userData.image, 120.dp)
                VerticalMargin(10)
                ContactName(userData.name, 20, 0xFFFFFCFF)
                VerticalMargin(2)
                ContactName(stringResource(id = R.string.snapchat), 12, 0xFFFFFFFF)
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    painter = painterResource(id = R.drawable.sc_chat),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.sc_dismiss, 60.dp) {
                        onEndVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.sc_dismiss_txt, 60.dp)
                }
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.sc_join, 60.dp) {
                        onStartVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.sc_join_txt, 60.dp)
                }
            }
            VerticalMargin(80)
        }
    }
}