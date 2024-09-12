package com.app.incroyable.videocall_prank.presentation.wallpaper.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.app.incroyable.videocall_prank.adhelper.LoadSmallNative
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNative
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNativeEnable
import com.app.incroyable.videocall_prank.core.utils.isOnline

@Composable
fun WallpaperBody(
    image: ArrayList<String>,
    modifier: Modifier = Modifier
) {
    GridListPattern(items = image)
}

@Composable
fun GridListPattern(items: List<Any>) {
    val context = LocalContext.current
    val placementId = context.getAdString(prefAdmobNative)

    LazyColumn {
        itemsIndexed(items.chunked(3)) { index, rowChunk ->
            if (context.getAdBoolean(prefAdmobNativeEnable) && placementId.isNotEmpty() && context.isOnline()) {
                if (index == 3) {
                    LoadSmallNative()
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val totalItems = rowChunk.size
                val totalSpace = 3 - totalItems
                rowChunk.forEach { item ->
                    GridItem(item = item.toString(), modifier = Modifier.weight(1f))
                }
                repeat(totalSpace) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun GridItem(item: String, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        WallpaperCard(image = item)
    }
}