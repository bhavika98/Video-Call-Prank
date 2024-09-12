package com.app.incroyable.videocall_prank.core.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_VIBRATE
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.core.storage.setDefaultPreferences
import com.app.incroyable.videocall_prank.theme.fontVarela

@Composable
fun VibrateDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val default = context.getDefaultPreferences(KEY_CALL_VIBRATE) as Boolean
    val isVibrate = remember { mutableStateOf(default) }

    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            isVibrate.value = true
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_vibrate_on),
                            contentDescription = null,
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 15.dp),
                            text = stringResource(id = R.string.vibrate_on),
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontFamily = fontVarela
                            )
                        )
                        if (isVibrate.value)
                            Image(
                                painter = painterResource(id = R.drawable.btn_done),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp),
                                contentScale = ContentScale.Crop
                            )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            isVibrate.value = false
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_vibrate_off),
                            contentDescription = null,
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 15.dp),
                            text = stringResource(id = R.string.vibrate_off),
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start,
                            style = TextStyle(
                                fontFamily = fontVarela
                            )
                        )
                        if (!isVibrate.value)
                            Image(
                                painter = painterResource(id = R.drawable.btn_done),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp),
                                contentScale = ContentScale.Crop
                            )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(Modifier.padding(top = 0.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp, bottom = 15.dp, top = 8.dp)
                            .height(40.dp)
                            .weight(1f)
                            .border(0.dp, Color.Transparent),
                        backgroundColor = Color(0xFFEDF1F7),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onDismiss()
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                color = Color.Black,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = fontVarela
                                )
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 16.dp, bottom = 15.dp, top = 8.dp)
                            .height(40.dp)
                            .weight(1f)
                            .border(0.dp, Color.Transparent),
                        backgroundColor = Color(0xFF364764),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    context.setDefaultPreferences(KEY_CALL_VIBRATE, isVibrate.value)
                                    onDismiss()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.set),
                                color = Color.White,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = fontVarela
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}