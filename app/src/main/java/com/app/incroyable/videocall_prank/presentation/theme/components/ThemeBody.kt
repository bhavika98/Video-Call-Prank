package com.app.incroyable.videocall_prank.presentation.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.incroyable.videocall_prank.core.storage.KEY_CALL_THEME
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.model.ThemeData

@Composable
fun ThemeBody(
    theme: List<ThemeData>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedThemeIndex = context.getDefaultPreferences(KEY_CALL_THEME)
    var selectedIndex by remember { mutableStateOf(selectedThemeIndex) }
    val onItemClick = { index: Int ->
        selectedIndex = index
    }
    LazyVerticalGrid(
        modifier = modifier.padding(3.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = theme
        ) { index ->
            ThemeCard(
                index = index.position,
                selected = selectedIndex == index.position,
                onClick = onItemClick,
                theme = index
            )
        }
    }
}
