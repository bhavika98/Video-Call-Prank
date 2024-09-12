package com.app.incroyable.videocall_prank.core.camera

import android.graphics.ImageFormat
import android.graphics.Point
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.util.Log
import android.view.Surface
import android.view.TextureView
import java.io.IOException
import java.util.Arrays
import kotlin.math.abs

class CameraHelper private constructor(builder: Builder) : Camera.PreviewCallback {
    private val TAG = "CameraHelper"
    private var mCamera: Camera? = null
    private var mCameraId: Int = 0
    private var previewViewSize: Point? = null
    private var previewDisplayView: TextureView = builder.previewDisplayView
    private var previewSize: Camera.Size? = null
    private var specificPreviewSize: Point? = builder.previewSize
    private var displayOrientation = 0
    private var rotation: Int = builder.rotation
    private var additionalRotation: Int = builder.additionalRotation
    private var isMirror: Boolean = builder.isMirror

    private var specificCameraId: Int? = null
    private var cameraListener: CameraListener? = null

    private val textureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureAvailable(
            surfaceTexture: SurfaceTexture,
            width: Int,
            height: Int
        ) {
            if (mCamera != null) {
                try {
                    mCamera!!.setPreviewTexture(surfaceTexture)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        override fun onSurfaceTextureSizeChanged(
            surfaceTexture: SurfaceTexture,
            width: Int,
            height: Int
        ) {
            Log.i(TAG, "onSurfaceTextureSizeChanged: $width  $height")
        }

        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture): Boolean {
            stop()
            return false
        }

        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) {}
    }

    init {
        previewDisplayView.surfaceTextureListener = textureListener
        if (isMirror) {
            previewDisplayView.scaleX = -1f
        }
    }

    fun start() {
        synchronized(this) {
            if (mCamera != null) {
                return
            }
            mCameraId = Camera.getNumberOfCameras() - 1
            if (specificCameraId != null && specificCameraId!! <= mCameraId) {
                mCameraId = specificCameraId!!
            }

            if (mCameraId == -1) {
                cameraListener?.onCameraError(Exception("camera not found"))
                return
            }
            if (mCamera == null) {
                mCamera = Camera.open(mCameraId)
            }
            displayOrientation = getCameraOri(rotation)
            mCamera!!.setDisplayOrientation(displayOrientation)
            try {
                val parameters = mCamera!!.parameters
                parameters.previewFormat = ImageFormat.NV21

                previewSize = parameters.previewSize
                val supportedPreviewSizes = parameters.supportedPreviewSizes
                if (supportedPreviewSizes != null && supportedPreviewSizes.size > 0) {
                    previewSize = getBestSupportedSize(supportedPreviewSizes, previewViewSize)
                }
                parameters.setPreviewSize(previewSize!!.width, previewSize!!.height)

                val supportedFocusModes = parameters.supportedFocusModes
                if (supportedFocusModes != null && supportedFocusModes.size > 0) {
                    if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                        parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
                    } else if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                        parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
                    } else if (supportedFocusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                        parameters.focusMode = Camera.Parameters.FOCUS_MODE_AUTO
                    }
                }
                mCamera!!.parameters = parameters
                mCamera!!.setPreviewTexture(previewDisplayView.surfaceTexture)
                mCamera!!.setPreviewCallbackWithBuffer(this)

                mCamera!!.addCallbackBuffer(
                    byteArrayOf(
                        previewSize!!.width.toByte(),
                        previewSize!!.height.toByte()
                    )
                )

                mCamera!!.startPreview()
                cameraListener?.onCameraOpened(mCamera!!, mCameraId, displayOrientation, isMirror)
            } catch (e: Exception) {
                cameraListener?.onCameraError(e)
            }
        }
    }

    private fun getCameraOri(rotation: Int): Int {
        var degrees = rotation * 90
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }
        additionalRotation /= 90
        additionalRotation *= 90
        degrees += additionalRotation

        val info = Camera.CameraInfo()
        Camera.getCameraInfo(mCameraId, info)
        val result = if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            val r = (info.orientation + degrees) % 360
            (360 - r) % 360
        } else {
            (info.orientation - degrees + 360) % 360
        }
        return result
    }

    fun stop() {
        synchronized(this) {
            if (mCamera == null) {
                return
            }
            mCamera!!.setPreviewCallback(null)
            mCamera!!.stopPreview()
            mCamera!!.release()
            mCamera = null
            cameraListener?.onCameraClosed()
        }
    }

    fun isStopped(): Boolean {
        synchronized(this) {
            return mCamera == null
        }
    }

    fun release() {
        synchronized(this) {
            stop()
            previewDisplayView.surfaceTextureListener = null
            specificCameraId = null
            cameraListener = null
            previewViewSize = null
            specificPreviewSize = null
            previewSize = null
        }
    }

    private fun getBestSupportedSize(
        sizes: List<Camera.Size>,
        previewViewSize: Point?
    ): Camera.Size {
        if (sizes.isEmpty()) {
            return mCamera!!.parameters.previewSize
        }
        val tempSizes = sizes.toTypedArray()
        Arrays.sort(tempSizes, object : Comparator<Camera.Size> {
            override fun compare(o1: Camera.Size, o2: Camera.Size): Int {
                return when {
                    o1.width > o2.width -> -1
                    o1.width == o2.width -> if (o1.height > o2.height) -1 else 1
                    else -> 1
                }
            }
        })
        var bestSize = tempSizes[0]
        val previewViewRatio = if (previewViewSize != null) {
            previewViewSize.x.toFloat() / previewViewSize.y.toFloat()
        } else {
            bestSize.width.toFloat() / bestSize.height.toFloat()
        }
        val isNormalRotate = additionalRotation % 180 == 0

        for (s in sizes) {
            if (specificPreviewSize != null && specificPreviewSize!!.x == s.width && specificPreviewSize!!.y == s.height) {
                return s
            }
            if (!isNormalRotate) {
                if (abs((s.height.toFloat() / s.width) - previewViewRatio) < Math.abs(
                        bestSize.height.toFloat() / bestSize.width - previewViewRatio
                    )
                ) {
                    bestSize = s
                }
            } else {
                if (abs((s.width.toFloat() / s.height) - previewViewRatio) < Math.abs(
                        bestSize.width.toFloat() / bestSize.height - previewViewRatio
                    )
                ) {
                    bestSize = s
                }
            }
        }
        return bestSize
    }

    fun getSupportedPreviewSizes(): List<Camera.Size>? {
        return mCamera?.parameters?.supportedPreviewSizes
    }

    fun getSupportedPictureSizes(): List<Camera.Size>? {
        return mCamera?.parameters?.supportedPictureSizes
    }

    @Deprecated("Deprecated in Java")
    override fun onPreviewFrame(nv21: ByteArray, camera: Camera) {
        camera.addCallbackBuffer(nv21)
        cameraListener?.onPreview(nv21, camera)
    }

    fun changeDisplayOrientation(rotation: Int) {
        if (mCamera != null) {
            this.rotation = rotation
            displayOrientation = getCameraOri(rotation)
            mCamera!!.setDisplayOrientation(displayOrientation)
            cameraListener?.onCameraConfigurationChanged(mCameraId, displayOrientation)
        }
    }

    class Builder {

        lateinit var previewDisplayView: TextureView

        var isMirror: Boolean = false

        var specificCameraId: Int? = null

        var cameraListener: CameraListener? = null

        var previewViewSize: Point? = null

        var rotation: Int = 0

        var previewSize: Point? = null

        var additionalRotation: Int = 0

        fun previewOn(value: TextureView): Builder {
            previewDisplayView = value
            return this
        }

        fun isMirror(value: Boolean): Builder {
            isMirror = value
            return this
        }

        fun previewSize(value: Point): Builder {
            previewSize = value
            return this
        }

        fun previewViewSize(value: Point): Builder {
            previewViewSize = value
            return this
        }

        fun rotation(value: Int): Builder {
            rotation = value
            return this
        }

        fun additionalRotation(value: Int): Builder {
            additionalRotation = value
            return this
        }

        fun specificCameraId(value: Int?): Builder {
            specificCameraId = value
            return this
        }

        fun cameraListener(value: CameraListener?): Builder {
            cameraListener = value
            return this
        }

        fun build(): CameraHelper {
            if (previewViewSize == null) {
                Log.e("TAG", "previewViewSize is null, now use default previewSize")
            }
            if (cameraListener == null) {
                Log.e("TAG", "cameraListener is null, callback will not be called")
            }
            if (!::previewDisplayView.isInitialized) {
                throw RuntimeException("you must preview on a textureView or a surfaceView")
            }
            return CameraHelper(this)
        }
    }

}

