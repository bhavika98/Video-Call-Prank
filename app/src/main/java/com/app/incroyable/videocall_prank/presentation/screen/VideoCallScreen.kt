package com.app.incroyable.videocall_prank.presentation.screen

import android.net.Uri
import android.view.SurfaceView
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.Player
import com.devbrackets.android.exomedia.core.video.scale.ScaleType
import com.devbrackets.android.exomedia.ui.widget.VideoView
import com.app.incroyable.videocall_prank.activity.CallScreenActivity
import com.app.incroyable.videocall_prank.apihelper.baseUrl
import com.app.incroyable.videocall_prank.apihelper.decodeUrl
import com.app.incroyable.videocall_prank.apihelper.videoUrl
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_RING
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_THEME
import com.app.incroyable.videocall_prank.core.storage.SAVED_COUNTER
import com.app.incroyable.videocall_prank.core.storage.SERVER_VIDEO
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.core.storage.getPrefList
import com.app.incroyable.videocall_prank.core.storage.updatePrefList
import com.app.incroyable.videocall_prank.core.utils.memberId
import com.app.incroyable.videocall_prank.core.widgets.AudioPlayerViewModel
import com.app.incroyable.videocall_prank.core.widgets.SurfaceViewCallback
import com.app.incroyable.videocall_prank.core.widgets.getPath
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.model.getDefaultRingPathById
import com.app.incroyable.videocall_prank.presentation.screen.layout1.Screen1End
import com.app.incroyable.videocall_prank.presentation.screen.layout1.Screen1Start
import com.app.incroyable.videocall_prank.presentation.screen.layout2.Screen2End
import com.app.incroyable.videocall_prank.presentation.screen.layout2.Screen2Start
import com.app.incroyable.videocall_prank.presentation.screen.layout3.Screen3End
import com.app.incroyable.videocall_prank.presentation.screen.layout3.Screen3Start
import com.app.incroyable.videocall_prank.presentation.screen.layout4.Screen4End
import com.app.incroyable.videocall_prank.presentation.screen.layout4.Screen4Start
import com.app.incroyable.videocall_prank.presentation.screen.layout5.Screen5End
import com.app.incroyable.videocall_prank.presentation.screen.layout5.Screen5Start
import com.app.incroyable.videocall_prank.presentation.screen.layout6.Screen6End
import com.app.incroyable.videocall_prank.presentation.screen.layout6.Screen6Start
import com.app.incroyable.videocall_prank.presentation.screen.layout7.Screen7End
import com.app.incroyable.videocall_prank.presentation.screen.layout7.Screen7Start
import java.io.File

@Composable
fun VideoCallScreen(userData: User) {

    val context = LocalContext.current
    val audioPlayerViewModel: AudioPlayerViewModel = viewModel()
    val callTheme = context.getDefaultPreferences(KEY_CALL_THEME) as Int

    var videoPath = ""
    if (userData.type == 0) {
        val position = userData.id - 1
//        val vidCounter = context.getPrefList(SERVER_VIDEO)[position]
//        var savedCounter = context.getPrefList(SAVED_COUNTER)[position]
//        if (savedCounter == 0) {
            videoPath =
                String.format("file:///android_asset/video/%s.mp4", memberId + userData.id)
//        } else {
//            try {
//                videoPath =
//                    decodeUrl(baseUrl) + decodeUrl(videoUrl) + userData.id + File.separator + memberId + userData.id + "_" + savedCounter + ".mp4"
//            } catch (e: Exception) {
//                videoPath =
//                    String.format("file:///android_asset/video/%s.mp4", memberId + userData.id)
//                e.printStackTrace()
//            }
//        }
//        if (savedCounter < vidCounter)
//            savedCounter += 1
//        else
//            savedCounter = 0
//        context.updatePrefList(position, savedCounter)
    } else {
        videoPath = getPath(context, Uri.parse(userData.video))!!
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        val startVideo = remember { mutableStateOf(false) }

        val commonStartClickListener: () -> Unit = {
            startVideo.value = true
        }

        val commonEndCallListener: () -> Unit = {
            (context as CallScreenActivity).endCall()
        }

        val videoView = remember {
            VideoView(context).apply {
                setMedia(Uri.parse(videoPath))
                setRepeatMode(Player.REPEAT_MODE_ALL)
                setScaleType(ScaleType.CENTER_CROP)
                requestFocus()
            }
        }

        val surfaceView = remember {
            SurfaceView(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                holder.addCallback(SurfaceViewCallback(context))
            }
        }

        if (!startVideo.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                val ringtoneId = context.getDefaultPreferences(KEY_CALL_RING) as Int
                val ringtonePath = getDefaultRingPathById(ringtoneId)
                audioPlayerViewModel.playSong(context, ringtoneId, ringtonePath)
                (context as CallScreenActivity).vibratePattern()
                AndroidView(factory = { surfaceView })
                when (callTheme) {
                    1 -> {
                        Screen1Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    2 -> {
                        Screen2Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    3 -> {
                        Screen3Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    4 -> {
                        Screen4Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    5 -> {
                        Screen5Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    6 -> {
                        Screen6Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    7 -> {
                        Screen7Start(
                            userData = userData,
                            onStartVideoClick = commonStartClickListener,
                            onEndVideoClick = commonEndCallListener
                        )
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                audioPlayerViewModel.pauseSong()
                (context as CallScreenActivity).pickCall()
                DisposableEffect(Unit) {
                    videoView.start()
                    onDispose { videoView.release() }
                }
                if (callTheme == 2) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            AndroidView(factory = { videoView })
                        }
                        Box(modifier = Modifier.weight(1f))
                    }
                } else
                    AndroidView(factory = { videoView })
                when (callTheme) {
                    1 -> {
                        Screen1End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    2 -> {
                        Screen2End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    3 -> {
                        Screen3End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    4 -> {
                        Screen4End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    5 -> {
                        Screen5End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    6 -> {
                        Screen6End(
                            onEndVideoClick = commonEndCallListener
                        )
                    }

                    7 -> {
                        Screen7End(
                            userData = userData,
                            onEndVideoClick = commonEndCallListener
                        )
                    }
                }
            }
        }
    }
}

