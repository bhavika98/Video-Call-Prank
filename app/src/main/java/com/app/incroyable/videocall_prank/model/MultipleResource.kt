package com.app.incroyable.videocall_prank.model

import com.google.gson.annotations.SerializedName

data class MultipleResource(

    @SerializedName("version")
    val version: Int,

    @SerializedName("wallpaper")
    val wallpaper: List<Int>,

    @SerializedName("video")
    val video: List<Int>,

    @SerializedName("data")
    val data: AppData
)
