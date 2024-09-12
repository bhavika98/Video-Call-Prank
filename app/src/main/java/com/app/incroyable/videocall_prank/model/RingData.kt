package com.app.incroyable.videocall_prank.model

import com.app.incroyable.videocall_prank.core.storage.defaultRing


data class RingData(
    var id: Int = 0,

    var title: String = "",

    var duration: String = ""
)

fun getDefaultRingPathById(id: Int): String {
    val ringList = defaultRing()
    val ringData = ringList.find { it.id == id }
    return getRingPath(ringData!!.title)
}

fun getRingPath(title: String): String {
    return title.lowercase().replace(" ", "_")
}
