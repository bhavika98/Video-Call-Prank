package com.app.incroyable.videocall_prank.core.dialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Progress
import com.downloader.Status
import com.app.incroyable.videocall_prank.apihelper.baseUrl
import com.app.incroyable.videocall_prank.apihelper.decodeUrl
import com.app.incroyable.videocall_prank.apihelper.wallpaperUrl
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.utils.toastMsg
import com.app.incroyable.videocall_prank.theme.custom_light_button
import com.app.incroyable.videocall_prank.theme.custom_light_text_sub_title_main
import com.app.incroyable.videocall_prank.theme.fontVarela
import java.io.File
import java.util.Locale

@Composable
fun DownloadDialog(
    image: String, onDismiss: () -> Unit
) {
    val progressState = remember { mutableStateOf(0L) }
    val progressTextState = remember { mutableStateOf("0%") }
    val downloadSpeedState = remember { mutableStateOf("") }

    DownloadingDialogContent(
        LocalContext.current,
        image,
        onDismiss,
        onProgressUpdated = { progress ->
            val progressPercent = progress.currentBytes * 100 / progress.totalBytes
            progressState.value = progressPercent
            progressTextState.value = "$progressPercent%"
            downloadSpeedState.value =
                getProgressDisplayLine(progress.currentBytes, progress.totalBytes)
        },
        onStartOrResumeUpdated = {
            progressState.value = 0
        },
        onCancelUpdated = {
            downloadSpeedState.value = ""
            progressState.value = 0
            progressTextState.value = "0%"
        },
        onErrorUpdated = {
            downloadSpeedState.value = ""
            progressState.value = 0
            progressTextState.value = "0%"
        }
    )
    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.downloading),
                    modifier = Modifier
                        .padding(8.dp, 16.dp, 8.dp, 5.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(), fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = fontVarela
                )
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        CustomLinearProgressIndicator(
                            progress = progressState.value.toFloat() / 100f,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = progressTextState.value,
                                color = custom_light_text_sub_title_main,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = fontVarela
                                )
                            )
                            Text(
                                text = downloadSpeedState.value,
                                color = custom_light_text_sub_title_main,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = fontVarela
                                )
                            )
                        }
                    }
                }
                Row(Modifier.padding(top = 0.dp)) {
//                    if (isError.value) {
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
//                                .height(40.dp)
//                                .weight(1f)
//                                .border(0.dp, Color.Transparent),
//                            backgroundColor = Color(0xFFEDF1F7),
//                            elevation = 0.dp,
//                            shape = RoundedCornerShape(10.dp)
//                        ) {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .clickable {
//                                        isRetryClicked.value = true
//                                    },
//                                contentAlignment = Alignment.Center,
//                            ) {
//                                Text(
//                                    text = stringResource(id = R.string.retry),
//                                    color = Color.Black,
//                                    fontSize = 14.sp,
//                                    textAlign = TextAlign.Center,
//                                    style = TextStyle(
//                                        fontFamily = fontVarela
//                                    )
//                                )
//                            }
//                        }
//                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
                            .height(40.dp)
                            .weight(1f)
                            .border(0.dp, Color.Transparent),
                        backgroundColor = Color(0xFF364764),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    PRDownloader.cancelAll()
                                    onDismiss()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                color = Color.White,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = fontVarela
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
) {
    Box(
        modifier = modifier
            .height(5.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {
        LinearProgressIndicator(
            progress = progress,
            color = custom_light_button,
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
        )
    }
}


@Composable
fun DownloadingDialogContent(
    context: Context,
    image: String,
    onDismiss: () -> Unit,
    onProgressUpdated: (Progress) -> Unit,
    onStartOrResumeUpdated: () -> Unit,
    onCancelUpdated: () -> Unit,
    onErrorUpdated: () -> Unit
) {
    var downloadIdThree by remember { mutableStateOf(0) }

    if (Status.RUNNING == PRDownloader.getStatus(downloadIdThree)) {
        PRDownloader.pause(downloadIdThree)
        return
    }

    if (Status.PAUSED == PRDownloader.getStatus(downloadIdThree)) {
        PRDownloader.resume(downloadIdThree)
        return
    }

    val videoPath = decodeUrl(baseUrl) + decodeUrl(wallpaperUrl) + image
    val downloadPath = context.cacheDir.path + File.separator

    downloadIdThree = PRDownloader.download(videoPath, downloadPath, image)
        .build()
        .setOnStartOrResumeListener {
            onStartOrResumeUpdated()
        }
        .setOnPauseListener { }
        .setOnCancelListener {
            onCancelUpdated()
            downloadIdThree = 0
            onDismiss()
        }
        .setOnProgressListener { progress ->
            onProgressUpdated(progress)
        }
        .start(
            object : OnDownloadListener {
                override fun onDownloadComplete() {
//                    MediaScannerConnection.scanFile(
//                        this@MoreImageActivity,
//                        arrayOf(sharePath),
//                        null,
//                        MediaScannerConnection.OnScanCompletedListener { _, _ -> }
//                    )
                    onDismiss()
                }

                override fun onError(error: Error?) {
                    context.toastMsg(context.getString(R.string.some_error_occurred))
                    onErrorUpdated()
                    onDismiss()
                }
            }
        )
}

fun getProgressDisplayLine(currentBytes: Long, totalBytes: Long): String {
    return getBytesToMBString(currentBytes) + "/" + getBytesToMBString(totalBytes)
}

fun getBytesToMBString(bytes: Long): String {
    return String.format(Locale.ENGLISH, "%.2fMB", bytes / (1024.00 * 1024.00))
}

