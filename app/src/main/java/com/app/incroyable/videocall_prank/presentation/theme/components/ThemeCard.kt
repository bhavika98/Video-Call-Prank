package com.app.incroyable.videocall_prank.presentation.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_THEME
import com.app.incroyable.videocall_prank.core.storage.setDefaultPreferences
import com.app.incroyable.videocall_prank.model.ThemeData
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun ThemeCard(
    index: Int, selected: Boolean, onClick: (Int) -> Unit,
    theme: ThemeData
) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(size = 12.dp),
        modifier = Modifier
            .padding(
                6.dp
            )
            .fillMaxWidth()
            .height(286.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    getTheme(LocalDarkTheme.current, customCardMain)
                )
                .fillMaxSize()
                .clickable {
                    onClick.invoke(index)
                    context.setDefaultPreferences(KEY_CALL_THEME, index)
                }
        ) {
            Image(
                painter = painterResource(id = theme.thumb),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                Image(
                    painter = painterResource(id = R.drawable.btn_done),
                    contentDescription = null,
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .padding(3.dp)
                        .align(Alignment.TopEnd),
                    contentScale = ContentScale.Fit
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                )
            }
        }
    }
}
