package com.app.incroyable.videocall_prank.presentation.start

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.activity.MainActivity
import com.app.incroyable.videocall_prank.adhelper.components.AdAttributionMore
import com.app.incroyable.videocall_prank.adhelper.showInterstitialAd
import com.app.incroyable.videocall_prank.core.dialog.BackDialog
import com.app.incroyable.videocall_prank.core.utils.moreApp
import com.app.incroyable.videocall_prank.core.utils.policyLink
import com.app.incroyable.videocall_prank.core.utils.redirectApp
import com.app.incroyable.videocall_prank.core.utils.shareApp
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin
import com.app.incroyable.videocall_prank.theme.fontVarela

@Composable
fun StartScreen(
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val showDialog = remember { mutableStateOf(false) }

    BackHandler {
        showDialog.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            context.shareApp()
                        },
                    painter = painterResource(id = R.drawable.icon_share),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            context.redirectApp(context.packageName)
                        },
                    painter = painterResource(id = R.drawable.icon_rate),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            context.policyLink()
                        },
                    painter = painterResource(id = R.drawable.icon_policy),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Box(modifier = Modifier.width(60.dp)) {
                    Image(
                        modifier = Modifier
                            .width(70.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                context.moreApp()
                            },
                        painter = painterResource(id = R.drawable.icon_more),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    AdAttributionMore()
                }
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.start_title),
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 2.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontFamily = fontVarela
                )
                Text(
                    text = stringResource(id = R.string.start_subtitle),
                    modifier = Modifier
                        .padding(8.dp, 2.dp, 8.dp, 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    fontSize = 12.sp,
                    color = Color(0XFF979797),
                    textAlign = TextAlign.Center,
                    fontFamily = fontVarela
                )
                Card(
                    modifier = Modifier
                        .size(120.dp, 45.dp)
                        .padding(start = 8.dp, end = 16.dp, top = 8.dp)
                        .border(0.dp, Color.Transparent),
                    backgroundColor = Color(0xFF364764),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        MainActivity::class.java
                                    ).setAction("")
                                )
                                context.showInterstitialAd()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.start),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontFamily = fontVarela
                            )
                        )
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                Image(
                    painter = painterResource(R.drawable.start),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
//                LoadMediumNative()
            }
        }
    }

    if (showDialog.value) {
        BackDialog(
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false }
        )
    }
}