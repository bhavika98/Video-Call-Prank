package com.app.incroyable.videocall_prank.core.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.incroyable.videocall_prank.core.storage.defaultEmoji
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun EmojiDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() }, properties = DialogProperties(
                dismissOnBackPress = true, dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(300.dp),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = getTheme(LocalDarkTheme.current, customBackground)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(
                        items = defaultEmoji()
                    ) { index ->
                        Card(
                            backgroundColor = getTheme(LocalDarkTheme.current, customCardMain),
                            shape = RoundedCornerShape(size = 12.dp),
                            modifier = Modifier
                                .padding(
                                    6.dp
                                )
                                .width(50.dp)
                                .height(50.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clickable {
                                        onItemClick(index)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = index,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

