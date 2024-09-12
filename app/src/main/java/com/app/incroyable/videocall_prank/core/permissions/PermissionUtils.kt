package com.app.incroyable.videocall_prank.core.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

private val storagePermissions =
    if (isUpsideDownCakeDevice()) {
        listOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        )
    } else if (isTiramisuDevice()) {
        listOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
        )
    } else {
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

private val cameraPermissions = listOf(
    Manifest.permission.CAMERA
)

private fun isTiramisuDevice(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}

private fun isUpsideDownCakeDevice(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
}

fun checkCameraPermission(
    context: Context,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (
        token: PermissionToken?,
        redirect: Boolean
    ) -> Unit
) {
    val permissionsToRequest = cameraPermissions.filter {
        ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
    }.toTypedArray()

    if (permissionsToRequest.isNotEmpty()) {
        askPermission(context, cameraPermissions, onPermissionGranted, onPermissionDenied)
    } else {
        onPermissionGranted()
    }
}

fun checkStoragePermission(
    context: Context,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (
        token: PermissionToken?,
        redirect: Boolean
    ) -> Unit
) {
    val permissionsToRequest = storagePermissions.filter {
        ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
    }.toTypedArray()

    if (permissionsToRequest.isNotEmpty()) {
        askPermission(context, storagePermissions, onPermissionGranted, onPermissionDenied)
    } else {
        onPermissionGranted()
    }
}

private fun askPermission(
    context: Context,
    mainPermissions: List<String>,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (
        token: PermissionToken?,
        redirect: Boolean
    ) -> Unit
) {
    Dexter.withContext(context)
        .withPermissions(mainPermissions)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    onPermissionGranted()
                }
                if (report.isAnyPermissionPermanentlyDenied) {
                    onPermissionDenied(null, true)
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: MutableList<PermissionRequest>,
                token: PermissionToken
            ) {
                onPermissionDenied(token, false)
            }
        })
        .onSameThread()
        .check()
}

const val requestCodeSetting: Int = 2
fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.fromParts("package", context.packageName, null)
    if (context is Activity) {
        context.startActivityForResult(intent, requestCodeSetting)
    }
}

const val requestCodeOverlay: Int = 3

@RequiresApi(Build.VERSION_CODES.M)
fun openOverlaySettings(context: Context) {
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    intent.data = Uri.fromParts("package", context.packageName, null)
    if (context is Activity) {
        context.startActivityForResult(intent, requestCodeOverlay)
    }
}