package com.app.incroyable.videocall_prank.core.dialog

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.edit
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.utils.redirectApp
import com.app.incroyable.videocall_prank.theme.fontVarela
import java.text.SimpleDateFormat
import java.util.Date

private const val SHOW_NEVER = "show_never"
private const val SECOND_TIME = "second_time"
private const val LAST_DATE = "last_date"

@SuppressLint("SimpleDateFormat")
@Composable
fun RateDialog(
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit
) {
    val ratePrefs = "RateDialog"
    val context = LocalContext.current
    val sharedPreferences =
        remember { context.getSharedPreferences(ratePrefs, Context.MODE_PRIVATE) }

    val secondTime = sharedPreferences.getBoolean(SECOND_TIME, false)
    if (!secondTime)
        sharedPreferences.edit { putBoolean(SECOND_TIME, true) }
    else {
        val currentDate = SimpleDateFormat("yyyyMMdd").format(Date())
        if (sharedPreferences.getString(LAST_DATE, "") != currentDate) {
            if (!sharedPreferences.getBoolean(SHOW_NEVER, false)) {
                Dialog(
                    onDismissRequest = { onNegativeButtonClick() }, properties = DialogProperties(
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
                                .padding(start = 5.dp, end = 5.dp)
                                .background(Color.White),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(150.dp)
                                    .padding(top = 20.dp)
                                    .align(Alignment.CenterHorizontally),
                                painter = painterResource(id = R.drawable.rating),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = stringResource(id = R.string.rate_title),
                                modifier = Modifier
                                    .padding(8.dp, 8.dp, 8.dp, 2.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = fontVarela
                            )
                            Text(
                                text = stringResource(id = R.string.rate_message),
                                modifier = Modifier
                                    .padding(8.dp, 2.dp, 8.dp, 16.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                fontSize = 12.sp,
                                color = Color(0XFF979797),
                                textAlign = TextAlign.Center,
                                fontFamily = fontVarela
                            )
                            Text(
                                text = stringResource(id = R.string.rate_thank_you),
                                modifier = Modifier
                                    .padding(8.dp, 2.dp, 8.dp, 16.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                color = Color(0xFF364764),
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = fontVarela
                            )
                            Row(Modifier.padding(top = 0.dp)) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 8.dp, top = 8.dp)
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
                                                onNegativeButtonClick()
                                            },
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.later),
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
                                        .padding(start = 8.dp, end = 16.dp, top = 8.dp)
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
                                                sharedPreferences.edit {
                                                    putBoolean(SHOW_NEVER, true)
                                                }
                                                context.redirectApp(context.packageName)
                                                onPositiveButtonClick()
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.rate_now),
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
            sharedPreferences.edit { putString(LAST_DATE, currentDate) }
        }
    }
}