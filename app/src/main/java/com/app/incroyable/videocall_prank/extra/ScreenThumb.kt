package com.app.incroyable.videocall_prank.extra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import com.app.incroyable.videocall_prank.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.core.widgets.AdjustableImage
import com.app.incroyable.videocall_prank.core.widgets.CircleImage
import com.app.incroyable.videocall_prank.core.widgets.ContactName
import com.app.incroyable.videocall_prank.core.widgets.HorizontalMargin
import com.app.incroyable.videocall_prank.core.widgets.VerticalMargin

const val screenId = 5

class ScreenThumb : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ScreenAll()
        }
    }
}

@Preview(device = "id:pixel")
@Composable
fun ScreenAllPreview() {
    ScreenAll()
}

@Composable
fun ScreenAll() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(58.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                when (screenId) {
                    1 -> {
                        Screen1()
                    }

                    2 -> {
                        Screen2()
                    }

                    3 -> {
                        Screen3()
                    }

                    4 -> {
                        Screen4()
                    }

                    5 -> {
                        Screen5()
                    }

                    6 -> {
                        Screen6()
                    }

                    7 -> {
                        Screen7()
                    }
                }
            }
            Box(
                modifier = Modifier
                    .height(58.dp)
            )
        }
    }
}

@Composable
fun Screen1() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.start),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(30)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AdjustableImage(R.drawable.wa_down, 15.dp)
                AdjustableImage(R.drawable.wa_encrypted, 240.dp)
                AdjustableImage(R.drawable.wa_add, 15.dp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                CircleImage(R.drawable.wa_cut, 60.dp) {

                }
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(140.dp)
                        .background(Color.White)
                        .align(Alignment.CenterEnd)
                        .border(2.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            VerticalMargin(40)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AdjustableImage(R.drawable.wa_rotate, 19.dp)
                AdjustableImage(R.drawable.wa_video_camera, 19.dp)
                AdjustableImage(R.drawable.wa_microphone, 15.dp)
            }
            VerticalMargin(40)
        }
    }
}

@Composable
fun Screen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.start),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(25)
            Row(modifier = Modifier.fillMaxWidth()) {
                HorizontalMargin(value = 25)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircleImage(R.drawable.insta_minimize, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_video_call, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_mute, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_rotate, 35.dp) {
                    }
                    CircleImage(R.drawable.insta_close, 35.dp) {
                    }
                }
                HorizontalMargin(value = 25)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                AdjustableImage(R.drawable.insta_effects, 35.dp)
                HorizontalMargin(value = 25)
                AdjustableImage(R.drawable.insta_media, 35.dp)
                HorizontalMargin(value = 25)
                AdjustableImage(R.drawable.insta_add, 35.dp)
                HorizontalMargin(value = 25)
            }
            VerticalMargin(25)
        }
    }
}

@Composable
fun Screen3() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.start),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .background(Color.White)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(30)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AdjustableImage(image = R.drawable.fm_left_arrow, width = 20.dp)
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(160.dp)
                        .background(Color.White)
                        .border(2.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AdjustableImage(image = R.drawable.fm_effect, width = 30.dp)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            AdjustableImage(R.drawable.fm_swipe, 100.dp)
            VerticalMargin(5)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.fm_box),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CircleImage(R.drawable.fm_video, 55.dp) {}
                    CircleImage(R.drawable.fm_flip, 55.dp) {}
                    CircleImage(R.drawable.fm_mute, 55.dp) {}
                    CircleImage(R.drawable.fm_end, 55.dp) {

                    }
                }
            }
        }
    }
}

@Composable
fun Screen4() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.start),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .background(Color.White)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(30)
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .padding(end = 30.dp)
                    .align(Alignment.End)
                    .background(Color.White)
                    .border(2.dp, Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ft_box),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_effects, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_effects_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_mute, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_mute_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_flip, 45.dp) {

                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_flip_txt, 60.dp)
                    }
                    Column(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircleImage(R.drawable.ft_end, 45.dp) {
                        }
                        VerticalMargin(8)
                        AdjustableImage(R.drawable.ft_end_txt, 60.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun Screen5() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.start),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.sc_back, 20.dp)
                Image(
                    modifier = Modifier
                        .width(65.dp)
                        .height(35.dp)
                        .clickable(
                        ) {
                        },
                    painter = painterResource(id = R.drawable.sc_end),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.sc_speaker, 55.dp)
                AdjustableImage(R.drawable.sc_mute, 55.dp)
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                AdjustableImage(R.drawable.sc_video, 55.dp)
                AdjustableImage(R.drawable.sc_flip, 55.dp)
            }
            VerticalMargin(30)
        }
    }
}

@Composable
fun Screen6() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.start),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
            ) {
                AdjustableImage(R.drawable.gd_duo, 115.dp)
                HorizontalMargin(value = 10)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
                    .align(Alignment.Start)
                    .padding(start = 20.dp)
            ) {
                HorizontalMargin(value = 10)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .border(2.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            VerticalMargin(30)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdjustableImage(R.drawable.gd_video, 55.dp)
                AdjustableImage(R.drawable.gd_mute, 55.dp)
                CircleImage(R.drawable.gd_end, 65.dp) {
                }
                AdjustableImage(R.drawable.gd_flip, 55.dp)
                AdjustableImage(R.drawable.gd_filter, 55.dp)
            }
            VerticalMargin(30)
        }
    }
}

@Composable
fun Screen7() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.start),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Black.copy(alpha = 0.2f))
//        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VerticalMargin(20)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.sp_chatting, 45.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "J-Hope",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.wrapContentWidth()
                    )
                    ContactName("00:05:20", 12, 0xFFFFFFFF)
                }
            }
            VerticalMargin(10)
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(150.dp)
                        .background(Color.White)
                        .border(2.dp, Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AdjustableImage(image = R.drawable.sp_rotate, width = 30.dp)
                    }
                }
                HorizontalMargin(value = 15)
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalMargin(value = 10)
                AdjustableImage(R.drawable.sp_heart, 40.dp)
                HorizontalMargin(value = 20)
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleImage(R.drawable.sp_audio_btn, 45.dp) {}
                    CircleImage(R.drawable.sp_video_btn, 45.dp) {}
                    CircleImage(R.drawable.sp_end, 45.dp) {
                    }
                }
                HorizontalMargin(value = 20)
                AdjustableImage(R.drawable.sp_menu, 35.dp)
                HorizontalMargin(value = 10)
            }
            VerticalMargin(40)
        }
    }
}