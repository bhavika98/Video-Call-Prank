package com.app.incroyable.videocall_prank.presentation.main.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.activity.SetCallActivity
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.dialog.RemoveDialog
import com.app.incroyable.videocall_prank.core.storage.borderColor
import com.app.incroyable.videocall_prank.core.storage.fromAssets
import com.app.incroyable.videocall_prank.core.utils.imageJPG
import com.app.incroyable.videocall_prank.core.utils.profileImages
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.USER_DATA
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme
import java.io.File

var counter = 0

@Composable
fun MainCard(
    userViewModel: UserViewModel,
    user: User
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val showDialog = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 6.dp,
                bottom = 6.dp
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(
                    getTheme(LocalDarkTheme.current, customCardMain)
                )
                .clickable {
                    val intent = Intent(context, SetCallActivity::class.java).apply {
                        putExtra(USER_DATA, user)
                    }
                    context.startActivity(intent)
                    context.showInterstitialAd()
                }
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            counter = (counter + 1) % borderColor.size
            Box(
                modifier = Modifier
                    .size(46.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = borderColor[counter],
                            shape = CircleShape
                        )
                ) {
                    if (user.image.contains(File.separator)) {
                        GlideImage(
                            imageModel = { user.image },
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape),
                            previewPlaceholder = R.drawable.avatar
                        )
                    } else {
                        val bitmap =
                            LocalContext.current.fromAssets(profileImages + user.image + imageJPG)
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                        )
                    }
                }
                Text(
                    text = user.emoji,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = user.name,
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    color = getTheme(LocalDarkTheme.current, customTextTitle)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = user.contact,
                    fontFamily = fontVarela,
                    fontSize = 12.sp,
                    color = getTheme(LocalDarkTheme.current, customTextSubtitle)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            if (user.type == 0) {
                Image(
                    modifier = Modifier
                        .width(14.dp)
                        .rotate(180f)
                        .wrapContentHeight(),
                    painter = painterResource(id = R.drawable.icon_back),
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customTextSubtitle
                        )
                    ),
                    contentDescription = null
                )
            } else {
                Image(
                    modifier = Modifier
                        .width(14.dp)
                        .wrapContentHeight()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            showDialog.value = true
                        },
                    painter = painterResource(id = R.drawable.icon_delete),
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customTextSubtitle
                        )
                    ),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
    if (showDialog.value) {
        RemoveDialog(
            user = user,
            userViewModel,
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false }
        )
    }
}
