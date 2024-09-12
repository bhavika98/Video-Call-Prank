package com.app.incroyable.videocall_prank.presentation.setcall.components

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karumi.dexter.PermissionToken
import com.skydoves.landscapist.glide.GlideImage
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.activity.MemberActivity
import com.app.incroyable.videocall_prank.activity.UpdateContactActivity
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.dialog.OverlayPermissionDialog
import com.app.incroyable.videocall_prank.core.dialog.PermissionDialog
import com.app.incroyable.videocall_prank.core.permissions.checkCameraPermission
import com.app.incroyable.videocall_prank.core.storage.fromAssets
import com.app.incroyable.videocall_prank.core.utils.NetworkError
import com.app.incroyable.videocall_prank.core.utils.imageJPG
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.core.utils.profileImages
import com.app.incroyable.videocall_prank.core.utils.toastMsg
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.KEY_CONTACT
import com.app.incroyable.videocall_prank.database.network.KEY_EMOJI
import com.app.incroyable.videocall_prank.database.network.KEY_ID
import com.app.incroyable.videocall_prank.database.network.KEY_IMAGE
import com.app.incroyable.videocall_prank.database.network.KEY_NAME
import com.app.incroyable.videocall_prank.database.network.KEY_TYPE
import com.app.incroyable.videocall_prank.database.network.KEY_VIDEO
import com.app.incroyable.videocall_prank.database.network.UPDATED_USER_DATA
import com.app.incroyable.videocall_prank.database.network.USER_DATA
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.receiver.CallBroadcastReceiver
import com.app.incroyable.videocall_prank.theme.customBottomIcon
import com.app.incroyable.videocall_prank.theme.customButton
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.customTextTitleOpposite
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme
import java.io.File

@Composable
fun SetCallBody(
    user: User,
    darkTheme: Boolean
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val showOverlayDialog = remember { mutableStateOf(false) }

    var userData by remember { mutableStateOf(user) }
    var sliderPosition by remember { mutableStateOf(2f) }

    val showDialog = remember { mutableStateOf(false) }
    val showErrorDialog = remember { mutableStateOf(false) }
    var mRedirect by remember { mutableStateOf(false) }
    val mToken = remember { mutableStateOf<PermissionToken?>(null) }

    val updateLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val updatedUserData = data?.getSerializableExtra(UPDATED_USER_DATA) as User
            userData = updatedUserData
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            when (user.type) {
                                0 -> {
                                    updateLauncher.launch(
                                        Intent(
                                            context,
                                            MemberActivity::class.java
                                        ).apply {
                                            putExtra(USER_DATA, userData)
                                        })
                                }

                                1 -> {
                                    updateLauncher.launch(
                                        Intent(
                                            context,
                                            UpdateContactActivity::class.java
                                        ).apply {
                                            putExtra(USER_DATA, userData)
                                        })
                                }
                            }
                            context.showInterstitialAd()
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = getTheme(darkTheme, customBottomIcon),
                                shape = CircleShape
                            )
                    ) {
                        if (userData.image.contains(File.separator)) {
                            GlideImage(
                                imageModel = { userData.image },
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                previewPlaceholder = R.drawable.avatar
                            )
                        } else {
                            val bitmap =
                                LocalContext.current.fromAssets(profileImages + userData.image + imageJPG)
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.icon_edit),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp, bottom = 6.dp)
                            .align(Alignment.BottomEnd)
                            .height(22.dp)
                            .width(22.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = userData.name,
                textAlign = TextAlign.Center,
                fontFamily = fontVarela,
                fontSize = 15.sp,
                color = getTheme(LocalDarkTheme.current, customTextTitle),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = userData.contact,
                fontFamily = fontVarela,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = getTheme(LocalDarkTheme.current, customTextSubtitle),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.delay_time),
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = getTheme(LocalDarkTheme.current, customTextTitle),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 15.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.call_after),
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = getTheme(LocalDarkTheme.current, customTextSubtitle),
                    modifier = Modifier
                        .wrapContentWidth()
                )
                Text(
                    text = "${sliderPosition.toInt()}",
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = getTheme(LocalDarkTheme.current, customTextTitle),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 5.dp, end = 5.dp)
                )
                Text(
                    text = stringResource(id = R.string.seconds),
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = getTheme(LocalDarkTheme.current, customTextSubtitle),
                    modifier = Modifier
                        .wrapContentWidth()
                )
            }
            Box(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
            ) {
                Slider(
                    value = sliderPosition,
                    onValueChange = { newPosition ->
                        sliderPosition = newPosition
                    },
                    valueRange = 2f..60f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = Color.LightGray
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 15.dp, end = 15.dp),
                backgroundColor = getTheme(LocalDarkTheme.current, customButton),
                elevation = 0.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(
                                    context
                                )
                            ) {
                                showOverlayDialog.value = true
                            } else {
                                checkCameraPermission(context,
                                    onPermissionGranted = {
                                        showErrorDialog.value = true
                                    },
                                    onPermissionDenied = { token, redirect ->
                                        if (token != null)
                                            mToken.value = token
                                        mRedirect = redirect
                                        showDialog.value = true
                                    })
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.start_video_call),
                        color = getTheme(LocalDarkTheme.current, customTextTitleOpposite),
                        textAlign = TextAlign.Center,
                        fontFamily = fontVarela,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        style = TextStyle(color = Color.Black)
                    )
                }
            }
        }
    }
    if (showDialog.value) {
        PermissionDialog(
            token = mToken.value,
            redirect = mRedirect,
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false }
        )
    }
    if (showOverlayDialog.value) {
        OverlayPermissionDialog(
            onDismiss = { showOverlayDialog.value = false }
        )
    }
    if (showErrorDialog.value) {
        context.NetworkError(
            onNetworkAvailable = {
                if (!context.isOnline()) {
                    showErrorDialog.value = true
                } else {
                    showErrorDialog.value = false
                    callStart(context, sliderPosition.toInt(), userData)
                }
            },
            negativeClickListener = {
                showErrorDialog.value = false
            }
        )
    }
}

fun callStart(context: Context, value: Int, user: User) {
    val intent = Intent(
        context,
        CallBroadcastReceiver::class.java
    )
    intent.putExtra(KEY_ID, user.id)
    intent.putExtra(KEY_TYPE, user.type)
    intent.putExtra(KEY_NAME, user.name)
    intent.putExtra(KEY_CONTACT, user.contact)
    intent.putExtra(KEY_IMAGE, user.image)
    intent.putExtra(KEY_VIDEO, user.video)
    intent.putExtra(KEY_EMOJI, user.emoji)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        234324243,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
    )
    (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?)!![AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (value * 1000).toLong()] =
        pendingIntent
    val toastMessage = context.getString(R.string.call_set_seconds, value)
    context.toastMsg(toastMessage)
    try {
        val mIntent = Intent(Intent.ACTION_MAIN)
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        mIntent.addCategory(Intent.CATEGORY_HOME)
        context.startActivity(mIntent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}