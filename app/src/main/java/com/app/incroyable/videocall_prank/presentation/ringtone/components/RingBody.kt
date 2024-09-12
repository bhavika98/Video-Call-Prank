package com.app.incroyable.videocall_prank.presentation.ringtone.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.app.incroyable.videocall_prank.adhelper.LoadMediumNative
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_RING
import com.app.incroyable.videocall_prank.core.storage.defaultRing
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdInt
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNative
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNativeEnable
import com.app.incroyable.videocall_prank.core.storage.prefNativeList
import com.app.incroyable.videocall_prank.core.storage.prefNativeListDefault
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.model.RingData

@Composable
fun RingBody(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    val placementId = context.getAdString(prefAdmobNative)

    val selectedThemeIndex = context.getDefaultPreferences(KEY_CALL_RING)
    var selectedIndex by remember { mutableStateOf(selectedThemeIndex) }
    val onItemClick = { index: Int ->
        selectedIndex = index
    }
    var isInterrupted by remember { mutableStateOf(0) }

    val rings = defaultRing()
    val adItem = context.getAdInt(prefNativeList, prefNativeListDefault)
    val combinedList = buildList {
        if (adItem == 3) {
            addAll(rings.take(adItem))
            if (context.getAdBoolean(prefAdmobNativeEnable) && placementId.isNotEmpty() && context.isOnline()) {
                add(Unit)
            }
            addAll(rings.drop(adItem))
        } else {
            val totalChunks = (rings.size + (adItem - 1)) / adItem
            for (i in 0 until totalChunks) {
                val startIndex = i * adItem
                val endIndex = minOf(startIndex + adItem, rings.size)
                val chunk = rings.subList(startIndex, endIndex)
                addAll(chunk)
                if (context.getAdBoolean(prefAdmobNativeEnable) && placementId.isNotEmpty() && context.isOnline() && i < totalChunks - 1) {
                    add(Unit)
                }
            }
        }
    }

    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = combinedList
        ) { item ->
            when (item) {
                is Unit -> {
                    LoadMediumNative()
                }

                is RingData -> {
                    RingCard(
                        index = item.id,
                        selected = selectedIndex == item.id,
                        onClick = onItemClick,
                        item = item,
                        isInterrupted = isInterrupted,
                        onItemSelected = { selected ->
                            isInterrupted = selected
                            onItemSelected(selected)
                        }
                    )
                }
            }
        }
    }
}
