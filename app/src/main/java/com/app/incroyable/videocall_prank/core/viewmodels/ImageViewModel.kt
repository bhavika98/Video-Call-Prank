package com.app.incroyable.videocall_prank.core.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    companion object {
        var myPrefs by mutableStateOf(AppPrefs())
    }
}

data class AppPrefs(
    val dataList: ArrayList<String> = ArrayList()
)


