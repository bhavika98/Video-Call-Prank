package com.app.incroyable.videocall_prank.presentation.ringtone.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_RING
import com.app.incroyable.videocall_prank.core.storage.setDefaultPreferences
import com.app.incroyable.videocall_prank.core.widgets.AudioPlayerViewModel
import com.app.incroyable.videocall_prank.model.RingData
import com.app.incroyable.videocall_prank.model.getRingPath
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun RingCard(
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit,
    item: RingData,
    isInterrupted: Int,
    onItemSelected: (Int) -> Unit
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val audioPlayerViewModel: AudioPlayerViewModel = viewModel()
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 6.dp,
                bottom = 6.dp
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(
                    getTheme(LocalDarkTheme.current, customCardMain)
                )
                .clickable {
                    onClick(index)
                    context.setDefaultPreferences(KEY_CALL_RING, index)
                }
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .selectable(
                        interactionSource = interactionSource,
                        indication = null,
                        selected = (isInterrupted == item.id),
                        onClick = {
                            val newSelected = if (isInterrupted == item.id) {
                                0
                            } else {
                                item.id
                            }
                            if (isInterrupted == item.id) {
                                audioPlayerViewModel.pauseSong()
                            } else {
                                audioPlayerViewModel.playSong(context, item.id, getRingPath(item.title))
                            }
                            onItemSelected(newSelected)
                        }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    val iconRes = if (isInterrupted == item.id) {
                        R.drawable.icon_pause
                    } else {
                        R.drawable.icon_play
                    }
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(45.dp)
                            .padding(5.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.title,
                    fontFamily = fontVarela,
                    fontSize = 14.sp,
                    color = getTheme(LocalDarkTheme.current, customTextTitle)
                )
                if (item.id != 1) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = item.duration,
                        fontFamily = fontVarela,
                        fontSize = 12.sp,
                        color = getTheme(LocalDarkTheme.current, customTextSubtitle)
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            if (selected) {
                Image(
                    painter = painterResource(id = R.drawable.btn_done),
                    contentDescription = null,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .padding(3.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
