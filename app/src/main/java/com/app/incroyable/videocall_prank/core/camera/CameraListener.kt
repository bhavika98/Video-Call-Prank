package com.app.incroyable.videocall_prank.core.camera

import android.hardware.Camera

interface CameraListener {

    fun onCameraOpened(camera: Camera, cameraId: Int, displayOrientation: Int, isMirror: Boolean)

    fun onPreview(data: ByteArray, camera: Camera)

    fun onCameraClosed()

    fun onCameraError(e: Exception)

    fun onCameraConfigurationChanged(cameraID: Int, displayOrientation: Int)
}
