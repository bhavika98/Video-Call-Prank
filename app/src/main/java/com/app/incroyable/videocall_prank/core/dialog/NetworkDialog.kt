package com.app.incroyable.videocall_prank.core.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.theme.fontVarela

@Composable
fun NetworkDialog(
    onNetworkAvailable: () -> Unit,
    negativeClickListener: () -> Unit
) {
    Dialog(
        onDismissRequest = { negativeClickListener() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
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
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.network_error),
                    modifier = Modifier
                        .padding(8.dp, 16.dp, 8.dp, 2.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(), fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = fontVarela
                )
                Text(
                    text = stringResource(id = R.string.check_your_connection),
                    modifier = Modifier
                        .padding(8.dp, 2.dp, 8.dp, 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = fontVarela
                )
                Row(Modifier.padding(top = 0.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp, bottom = 20.dp, top = 8.dp)
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
                                    onNetworkAvailable()
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.retry),
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
                            .padding(start = 8.dp, end = 16.dp, bottom = 20.dp, top = 8.dp)
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
                                    negativeClickListener()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.dismiss),
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

