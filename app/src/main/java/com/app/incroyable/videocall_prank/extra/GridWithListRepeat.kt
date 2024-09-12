package com.app.incroyable.videocall_prank.extra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class Item(val id: Int, val text: String)

@Composable
fun GridListPattern(items: List<Item>) {
    LazyColumn {
        items(items.chunked(10)) { chunk ->
            chunk.take(9).chunked(3).forEach { rowChunk ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val totalItems = rowChunk.size
                    val totalSpace = 3 - totalItems
                    rowChunk.forEach { item ->
                        GridItem(item = item, modifier = Modifier.weight(1f))
                    }
                    repeat(totalSpace) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            if (chunk.size == 10) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(item = chunk.last())
                }
            }
        }
    }
}

@Composable
fun GridItem(item: Item, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.text,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Composable
fun ListItem(item: Item) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.text,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Preview
@Composable
fun PreviewGridListPattern() {
    val previewItems = List(32) { Item(id = it, text = "Item $it") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        GridListPattern(items = previewItems)
    }
}