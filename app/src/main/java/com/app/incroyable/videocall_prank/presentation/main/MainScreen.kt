package com.app.incroyable.videocall_prank.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.core.dialog.RateDialog
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.presentation.main.components.MainBody
import com.app.incroyable.videocall_prank.presentation.main.components.MainBottomBar
import com.app.incroyable.videocall_prank.presentation.main.components.MainTopBar
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.getTheme
import kotlinx.coroutines.delay

val LocalDarkTheme = compositionLocalOf<Boolean> { error("No DarkTheme provided") }

@Composable
fun MainScreen(userViewModel: UserViewModel, darkTheme: Boolean, onThemeUpdated: () -> Unit) {
    val darkThemeState = remember { mutableStateOf(darkTheme) }
    val showRateDialog = remember { mutableStateOf(false) }

    LaunchedEffect(!showRateDialog.value) {
        if (!showRateDialog.value) {
            delay(2000)
            showRateDialog.value = true
        }
    }

    CompositionLocalProvider(LocalDarkTheme provides darkThemeState.value) {
        val users by userViewModel.users.observeAsState(emptyList())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(getTheme(darkThemeState.value, customBackground))
        ) {
            MainTopBar(
                darkTheme = darkThemeState.value,
                onThemeUpdated = {
                    darkThemeState.value = !darkThemeState.value
                    onThemeUpdated()
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            MainBody(
                userViewModel,
                users = users,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            MainBottomBar()
        }

        if (showRateDialog.value) {
            RateDialog(
                onPositiveButtonClick = {
                    showRateDialog.value = false
                },
                onNegativeButtonClick = {
                    showRateDialog.value = false
                }
            )
        }
    }
}







