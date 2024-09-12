package com.app.incroyable.videocall_prank.presentation.screen.layout3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.app.incroyable.videocall_prank.core.widgets.ProfileImage
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.model.testUser

@Preview(device = "id:pixel")
@Composable
fun Screen3StartPreview() {
    Screen3Start(
        userData = testUser(),
        onStartVideoClick = { },
        onEndVideoClick = { })
}

@Composable
fun Screen3Start(userData: User, onStartVideoClick: () -> Unit, onEndVideoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(150)
            ProfileImage(userData.image, 95.dp)
            VerticalMargin(10)
            ContactName(userData.name, 20, 0xFFFFFCFF)
            VerticalMargin(2)
            ContactName(stringResource(id = R.string.messenger), 10, 0xFFF5F5F5)

            Spacer(modifier = Modifier.weight(1f))

            VerticalMargin(20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.fm_decline, 60.dp) {
                        onEndVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.fm_decline_txt, 60.dp)
                }
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImage(R.drawable.fm_answer, 60.dp) {
                        onStartVideoClick()
                    }
                    VerticalMargin(12)
                    AdjustableImage(R.drawable.fm_answer_txt, 60.dp)
                }
            }
            VerticalMargin(60)
        }
    }
}