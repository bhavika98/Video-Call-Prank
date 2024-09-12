package com.mr0xf00.easycrop.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.mr0xf00.easycrop.CropState
import com.mr0xf00.easycrop.CropperStyle
import com.mr0xf00.easycrop.LocalCropperStyle
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customBottomIcon
import com.app.incroyable.videocall_prank.theme.customPanel
import com.app.incroyable.videocall_prank.theme.getTheme

private val CropperDialogProperties = @OptIn(ExperimentalComposeUiApi::class) (DialogProperties(
    usePlatformDefaultWidth = false,
    dismissOnBackPress = true,
    dismissOnClickOutside = false,
    decorFitsSystemWindows = true
))

@Composable
fun ImageCropperDialog(
    darkTheme: Boolean,
    state: CropState,
    style: CropperStyle = CropperStyle(darkTheme),
    dialogProperties: DialogProperties = CropperDialogProperties,
    topBar: @Composable (CropState) -> Unit = { DefaultTopBar1(darkTheme, it) },
    cropControls: @Composable BoxScope.(CropState) -> Unit = { DefaultControls(it) }
) {
    BackHandler {
//        MyStaticVariable.imgUri = null
        state.done(accept = false)
    }
    CompositionLocalProvider(LocalCropperStyle provides style) {
//        Dialog(
//            onDismissRequest = { state.done(accept = false) },
//            properties = dialogProperties
//        ) {
        Column(modifier = Modifier.fillMaxSize()) {
            topBar(state)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clipToBounds()
            ) {
                CropperPreview(darkTheme, state = state, modifier = Modifier.fillMaxSize())
                cropControls(state)
            }
        }
//        }
    }
}

@Composable
private fun BoxScope.DefaultControls(state: CropState) {
    val verticalControls =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    CropperControls(
        isVertical = verticalControls,
        state = state,
        modifier = Modifier
            .align(if (!verticalControls) Alignment.BottomCenter else Alignment.CenterEnd)
            .padding(12.dp),
    )
}

//@Composable
//private fun DefaultTopBar(state: CropState) {
//    TopAppBar(title = {},
//        navigationIcon = {
//            IconButton(onClick = { state.done(accept = false) }) {
//                Icon(Icons.Default.ArrowBack, null)
//            }
//        },
//        actions = {
//            IconButton(onClick = { state.reset() }) {
//                Icon(painterResource(R.drawable.restore), null)
//            }
//            IconButton(onClick = { state.done(accept = true) }, enabled = !state.accepted) {
//                Icon(Icons.Default.Done, null)
//            }
//        }
//    )
//}

@Composable
fun DefaultTopBar1(
    darkTheme: Boolean,
    state: CropState
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(getTheme(darkTheme, customPanel))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    end = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(65.dp)
                    .background(getTheme(darkTheme, customPanel))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
//                        MyStaticVariable.imgUri = null
                        state.done(accept = false)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_back),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customBottomIcon
                        )
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(12.dp),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(65.dp)
                    .background(getTheme(darkTheme, customPanel))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        state.reset()
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_reset),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customBottomIcon
                        )
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(14.dp),
                )
            }
            Box(
                modifier = Modifier
                    .width(55.dp)
                    .height(65.dp)
                    .background(getTheme(darkTheme, customPanel))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        state.done(accept = true)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_done),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(
                        getTheme(
                            LocalDarkTheme.current,
                            customBottomIcon
                        )
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(14.dp),
                )
            }
        }
    }
}
