package com.app.incroyable.videocall_prank.presentation.main.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karumi.dexter.PermissionToken
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.activity.AddContactActivity
import com.app.incroyable.videocall_prank.activity.RingtoneActivity
import com.app.incroyable.videocall_prank.activity.ThemeActivity
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.dialog.PermissionDialog
import com.app.incroyable.videocall_prank.core.dialog.VibrateDialog
import com.app.incroyable.videocall_prank.core.permissions.checkStoragePermission
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customBottomIcon
import com.app.incroyable.videocall_prank.theme.customPanel
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun MainBottomBar() {
    val mToken = remember { mutableStateOf<PermissionToken?>(null) }
    var mRedirect by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }
    val showVibrateDialog = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(getTheme(LocalDarkTheme.current, customPanel)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            iconRes = R.drawable.menu_ringtone,
            title = stringResource(id = R.string.menu_ringtone)
        ) {
            context.redirectToActivity(0)
        }
        ColoredDivider()
        BottomBarItem(
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            iconRes = R.drawable.menu_user,
            title = stringResource(id = R.string.menu_user)
        ) {
            checkStoragePermission(context,
                onPermissionGranted = {
                    context.redirectToActivity(1)
                },
                onPermissionDenied = { token, redirect ->
                    if (token != null)
                        mToken.value = token
                    mRedirect = redirect
                    showDialog.value = true
                })
        }
        ColoredDivider()
        BottomBarItem(
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            iconRes = R.drawable.menu_theme,
            title = stringResource(id = R.string.menu_theme)
        ) {
            context.redirectToActivity(2)
        }
        ColoredDivider()
        BottomBarItem(
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            iconRes = R.drawable.menu_vibration,
            title = stringResource(id = R.string.menu_vibration)
        ) {
            showVibrateDialog.value = true
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
    if (showVibrateDialog.value) {
        VibrateDialog(onDismiss = {
            showVibrateDialog.value = false
        })
    }
}

@Composable
fun ColoredDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(35.dp)
            .graphicsLayer(alpha = 0.2f)
            .background(getTheme(LocalDarkTheme.current, customTextSubtitle))
    ) {
        Divider(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        )
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource,
    iconRes: Int,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = "",
                colorFilter = ColorFilter.tint(getTheme(LocalDarkTheme.current, customBottomIcon)),
                modifier = Modifier
                    .size(26.dp)
                    .padding(2.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(), fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = fontVarela,
                color = getTheme(LocalDarkTheme.current, customTextTitle)
            )
        }
    }
}

fun Context.redirectToActivity(selectedIndex: Int) {
    when (selectedIndex) {
        0 -> {
            startActivity(Intent(this, RingtoneActivity::class.java))
        }

        1 -> {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        2 -> {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
    }
    showInterstitialAd()
}



