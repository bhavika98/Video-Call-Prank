package com.app.incroyable.videocall_prank.core.widgets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.hardware.Camera
import android.view.SurfaceHolder

class SurfaceViewCallback(private val context: Context) :
    SurfaceHolder.Callback {

    private var mCamera: Camera? = null
    private var isPreview = false
    private var numOfCam = 0

    override fun surfaceCreated(holder: SurfaceHolder) {
        try {
            val cameraAndIndex = openFrontFacingCameraGingerbread()
            mCamera = cameraAndIndex.first!!
            numOfCam = cameraAndIndex.second
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mCamera!!.parameters.supportedFocusModes.contains("auto")
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (isPreview) {
            mCamera!!.stopPreview()
            isPreview = false
        }
        try {
            mCamera!!.setPreviewDisplay(holder)
            mCamera!!.startPreview()
            isPreview = true
            setCameraDisplayOrientation(context, numOfCam, mCamera!!)
        } catch (_: java.lang.Exception) {
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        mCamera!!.stopPreview()
        mCamera!!.release()
        mCamera = null
        isPreview = false
    }
}

fun openFrontFacingCameraGingerbread(): Pair<Camera?, Int> {
    val cameraInfo = Camera.CameraInfo()
    val numberOfCameras = Camera.getNumberOfCameras()
    var camera: Camera? = null
    var numOfCam = 0
    for (i2 in 0 until numberOfCameras) {
        Camera.getCameraInfo(i2, cameraInfo)
        if (cameraInfo.facing == 1) {
            try {
                camera = Camera.open(i2)
                numOfCam = i2
            } catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }
    }
    return Pair(camera, numOfCam)
}

@SuppressLint("SwitchIntDef")
fun setCameraDisplayOrientation(context: Context, i2: Int, camera: Camera) {
    var i3 = 0
    val cameraInfo = Camera.CameraInfo()
    Camera.getCameraInfo(i2, cameraInfo)
    when ((context as Activity).windowManager.defaultDisplay.rotation) {
        1 -> i3 = 90
        2 -> i3 = 180
        3 -> i3 = 270
    }
    camera.setDisplayOrientation(if (cameraInfo.facing == 1) (360 - (i3 + cameraInfo.orientation) % 360) % 360 else (cameraInfo.orientation - i3 + 360) % 360)
}