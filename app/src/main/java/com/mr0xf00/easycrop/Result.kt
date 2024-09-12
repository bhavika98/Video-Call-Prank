package com.mr0xf00.easycrop

import android.graphics.Bitmap
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import com.mr0xf00.easycrop.images.getDecodeParams
import com.mr0xf00.easycrop.utils.ViewMat
import com.mr0xf00.easycrop.utils.atOrigin
import com.mr0xf00.easycrop.utils.coerceAtMost
import com.mr0xf00.easycrop.utils.roundUp
import com.mr0xf00.easycrop.utils.times
import com.app.incroyable.videocall_prank.core.storage.MyStaticVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Creates an [ImageBitmap] using the parameters in [CropState].
 * If [maxSize] is not null, the result will be scaled down to match it.
 * Returns null if the image could not be created.
 */
public suspend fun CropState.createResult(
    maxSize: IntSize?
): ImageBitmap? = withContext(Dispatchers.Default) {
    runCatching { doCreateResult(maxSize, MyStaticVariable.imgUri!!.path.toString()) }
        .onFailure { it.printStackTrace() }
        .getOrNull()
}

private suspend fun CropState.doCreateResult(
    maxSize: IntSize?,
    outputFilePath: String
): ImageBitmap? {
    val finalSize = region.size
        .coerceAtMost(maxSize?.toSize())
        .roundUp()
    val result = ImageBitmap(finalSize.width, finalSize.height)
    val canvas = Canvas(result)
    val viewMat = ViewMat()
    viewMat.snapFit(region, finalSize.toSize().toRect())
    val imgMat = transform.asMatrix(src.size)
    val totalMat = imgMat * viewMat.matrix

    canvas.clipPath(shape.asPath(region.atOrigin()))
    canvas.concat(totalMat)
    val inParams = getDecodeParams(view = finalSize, img = src.size, totalMat)
        ?: return null
    val decoded = src.open(inParams) ?: return null
    val paint = Paint().apply { filterQuality = FilterQuality.High }
    canvas.drawImageRect(
        image = decoded.bmp, paint = paint,
        dstOffset = decoded.params.subset.topLeft,
        dstSize = decoded.params.subset.size,
    )
    saveImageBitmapToFile(result, outputFilePath)
    return result
}

private suspend fun saveImageBitmapToFile(imageBitmap: ImageBitmap, outputFilePath: String) {
    withContext(Dispatchers.IO) {
        val bitmap: Bitmap = imageBitmap.asAndroidBitmap()
        val file = File(outputFilePath)
        file.parentFile?.mkdirs()
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()
    }
}
