package com.app.incroyable.videocall_prank.core.widgets

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.hardware.Camera
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.app.incroyable.videocall_prank.core.camera.CameraHelper
import com.app.incroyable.videocall_prank.core.camera.CameraListener
import com.app.incroyable.videocall_prank.core.camera.RoundTextureView
import kotlin.math.min

@Composable
fun CameraPreview(mRadius: Int) {
    val context = LocalContext.current

    val textureView = remember {
        createCustomRoundTextureView(context, mRadius)
    }

    DisposableEffect(Unit) {
        val cameraHelper = CameraHelper.Builder()
            .cameraListener(object : CameraListener {
                override fun onCameraOpened(
                    camera: Camera,
                    cameraId: Int,
                    displayOrientation: Int,
                    isMirror: Boolean
                ) {
                }

                override fun onPreview(data: ByteArray, camera: Camera) {
                }

                override fun onCameraClosed() {
                }

                override fun onCameraError(e: Exception) {
                }

                override fun onCameraConfigurationChanged(cameraID: Int, displayOrientation: Int) {
                }
            })
            .specificCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
            .previewOn(textureView)
            .previewViewSize(Point(textureView.layoutParams.width, textureView.layoutParams.height))
            .rotation((context as Activity).windowManager.defaultDisplay.rotation)
            .build()
        cameraHelper.start()

        onDispose {
            cameraHelper.release()
        }
    }

    AndroidView(factory = { textureView })
}

fun createCustomRoundTextureView(
    context: Context,
    mRadius: Int
): RoundTextureView {
    val textureView = RoundTextureView(context).apply {
        layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        viewTreeObserver.addOnGlobalLayoutListener {
            layoutParams = layoutParams.apply {
            }
            radius = mRadius * min(width, height) / 2 / 100
            turnRound()
        }
    }
    return textureView
}