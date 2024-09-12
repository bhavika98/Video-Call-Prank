package com.app.incroyable.videocall_prank.presentation.member.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.activity.WallpaperActivity
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.storage.fromAssets
import com.app.incroyable.videocall_prank.core.utils.NetworkError
import com.app.incroyable.videocall_prank.core.utils.imageJPG
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.core.utils.moreImage
import com.app.incroyable.videocall_prank.core.utils.profileImages
import com.app.incroyable.videocall_prank.database.network.USER_ID
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.getTheme
import java.io.File

@Composable
fun MemberCard(
    userId: Int,
    index: String, selected: Boolean,
    onClick: (String) -> Unit,
    image: String
) {
    val context = LocalContext.current
    val showErrorDialog = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .background(
                    getTheme(LocalDarkTheme.current, customCardMain)
                )
                .aspectRatio(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    showErrorDialog.value = true
                }
        ) {
            if (image.contains(File.separator)) {
                GlideImage(
                    imageModel = { image },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    previewPlaceholder = R.drawable.avatar
                )
            } else {
                if (image.contains(moreImage)) {
                    Card(
                        shape = RoundedCornerShape(size = 12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    getTheme(LocalDarkTheme.current, customCardMain)
                                )
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_add),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(35.dp)
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                colorFilter = ColorFilter.tint(
                                    getTheme(
                                        LocalDarkTheme.current,
                                        customTextSubtitle
                                    )
                                )
                            )
                        }
                    }
                } else {
                    val bitmap = LocalContext.current.fromAssets(profileImages + image + imageJPG)
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .background(Color.Black)
                )
                Image(
                    painter = painterResource(id = R.drawable.btn_done),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .padding(3.dp)
                        .align(Alignment.TopEnd),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
    if (showErrorDialog.value) {
        context.NetworkError(
            onNetworkAvailable = {
                if (!context.isOnline()) {
                    showErrorDialog.value = true
                } else {
                    showErrorDialog.value = false
                    if (image.contains(moreImage)) {
                        val intent = Intent(
                            context,
                            WallpaperActivity::class.java
                        ).apply {
                            putExtra(USER_ID, userId)
                        }
                        context.startActivity(intent)
                        context.showInterstitialAd()
                    } else
                        onClick.invoke(index)
                }
            },
            negativeClickListener = {
                showErrorDialog.value = false
            }
        )
    }
}
