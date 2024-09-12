package com.app.incroyable.videocall_prank.core.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.storage.fromAssets
import com.app.incroyable.videocall_prank.core.utils.imageJPG
import com.app.incroyable.videocall_prank.core.utils.profileImages
import java.io.File

@Composable
fun AdjustableImage(
    image: Int,
    width: Dp
) {
    Image(
        modifier = Modifier
            .width(width),
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ProfileImage(
    image: String,
    width: Dp
) {
    Box(
        modifier = Modifier
            .size(width)
    ) {
        Image(
            painter = painterResource(id = R.drawable.more_placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        if (image.contains(File.separator)) {
            GlideImage(
                imageModel = { image },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        } else {
            val bitmap =
                LocalContext.current.fromAssets(profileImages + image + imageJPG)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun CircleImage(
    image: Int,
    width: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable(onClick = onClick),
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun VerticalMargin(
    value: Int,
) {
    Spacer(modifier = Modifier.height(value.dp))
}

@Composable
fun HorizontalMargin(
    value: Int,
) {
    Spacer(modifier = Modifier.width(value.dp))
}

