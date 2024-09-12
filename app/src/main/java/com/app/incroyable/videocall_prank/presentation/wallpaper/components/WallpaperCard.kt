package com.app.incroyable.videocall_prank.presentation.wallpaper.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.apihelper.baseUrl
import com.app.incroyable.videocall_prank.apihelper.decodeUrl
import com.app.incroyable.videocall_prank.apihelper.wallpaperUrl
import com.app.incroyable.videocall_prank.core.dialog.DownloadDialog
import com.app.incroyable.videocall_prank.core.utils.NetworkError
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.core.utils.toastMsg
import com.app.incroyable.videocall_prank.core.viewmodels.ImageViewModel
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.getTheme
import java.io.File

@Composable
fun WallpaperCard(
    image: String
) {
    val context = LocalContext.current
    var currentImage by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val showErrorDialog = remember { mutableStateOf(false) }
    val downloadedImagesState = remember { mutableStateMapOf<String, Boolean>() }
    val isImageDownloaded = downloadedImagesState[image] ?: run {
        val isExist = checkIfExist(context, image)
        downloadedImagesState[image] = isExist
        isExist
    }
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                6.dp
            )
    ) {
        Box(
            modifier = Modifier
                .background(
                    getTheme(LocalDarkTheme.current, customCardMain)
                )
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable {
                    if (!isImageDownloaded)
                        showErrorDialog.value = true
                    else
                        context.toastMsg(context.getString(R.string.already_exist))
                }
        ) {
            val path = decodeUrl(baseUrl) + decodeUrl(wallpaperUrl) + image
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            )
            GlideImage(
                imageModel = { path },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            if (!isImageDownloaded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_download),
                        contentDescription = null,
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp)
                            .padding(5.dp)
                    )
                }
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
                    currentImage = image
                    showDialog.value = true
                }
            },
            negativeClickListener = {
                showErrorDialog.value = false
            }
        )
    }
    if (showDialog.value) {
        DownloadDialog(currentImage, onDismiss = {
            showDialog.value = false
            downloadedImagesState[image] = true
            updateList(image)
        })
    }
}

private fun updateList(image: String) {
    ImageViewModel.myPrefs = ImageViewModel.myPrefs.copy(
        dataList = ArrayList(ImageViewModel.myPrefs.dataList).apply {
            add(image)
        }
    )
}

private fun checkIfExist(context: Context, image: String): Boolean {
    val path = context.cacheDir.path + File.separator + image
    val file1 = File(path)
    return file1.exists()
}