package com.app.incroyable.videocall_prank.core.storage

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.utils.imageJPG
import com.app.incroyable.videocall_prank.core.utils.memberId
import com.app.incroyable.videocall_prank.core.utils.moreImage
import com.app.incroyable.videocall_prank.core.widgets.getPath
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.model.RingData
import com.app.incroyable.videocall_prank.model.ThemeData

val wallpaperTitle = listOf("Custom", "Jimin", "Jungkook", "V", "Jin", "Suga", "J-Hope", "RM")

val borderColor = arrayOf(
    Color(0xFFF9EEAE),
    Color(0xFFFACCBB),
    Color(0xFFF7EDB9),
    Color(0xFFFEBCC3),
    Color(0xFFC6F5FF),
    Color(0xFFFFC5AC),
    Color(0xFFBBEEBD)
)

fun defaultMembers(): List<User> {
    return listOf(
        User(
            0,
            0,
            "Jimin (Park Jimin)",
            "+82 38 2736 5462",
            memberId + "1",
            memberId + "1",
            "\uD83E\uDD70"
        ),
        User(
            0,
            0,
            "Jungkook (Jeon Jungkook)",
            "+82 54 5930 8730",
            memberId + "2",
            memberId + "2",
            "☺"
        ),
        User(
            0,
            0,
            "V (Kim Taehyung)",
            "+82 87 1736 2746",
            memberId + "3",
            memberId + "3",
            "\uD83D\uDE1C"
        ),
        User(
            0,
            0,
            "Jin (Kim Seokjin)",
            "+82 19 3846 2847",
            memberId + "4",
            memberId + "4",
            "\uD83E\uDD17"
        ),
        User(
            0,
            0,
            "Suga (Min Yoongi)",
            "+82 27 5630 2947",
            memberId + "5",
            memberId + "5",
            "\uD83D\uDE18"
        ),
        User(
            0,
            0,
            "J-Hope (Jung Hoseok)",
            "+82 48 2974 9765",
            memberId + "6",
            memberId + "6",
            "\uD83D\uDE07"
        ),
        User(
            0,
            0,
            "RM (Kim Namjoon)",
            "+82 19 3846 2847",
            memberId + "7",
            memberId + "7",
            "\uD83D\uDE0D"
        )
    )
}

fun defaultTheme(): List<ThemeData> {
    return listOf(
        ThemeData(position = 1, title = "WhatsApp", thumb = R.drawable.theme_1),
        ThemeData(position = 2, title = "Instagram", thumb = R.drawable.theme_2),
        ThemeData(position = 3, title = "Messenger", thumb = R.drawable.theme_3),
        ThemeData(position = 4, title = "FaceTime", thumb = R.drawable.theme_4),
        ThemeData(position = 5, title = "SnapChat", thumb = R.drawable.theme_5),
        ThemeData(position = 6, title = "Duo", thumb = R.drawable.theme_6),
        ThemeData(position = 7, title = "Skype", thumb = R.drawable.theme_7)
    )
}

fun defaultRing(): List<RingData> {
    return listOf(
        RingData(id = 1, title = "Default", duration = ""),
        RingData(id = 2, title = "Seven", duration = "00:32"),
        RingData(id = 3, title = "Dreamers", duration = "00:33"),
        RingData(id = 4, title = "Permission To Dance", duration = "00:30"),
        RingData(id = 5, title = "Black Swan", duration = "00:27"),
        RingData(id = 6, title = "Left And Right", duration = "00:30"),
        RingData(id = 7, title = "On", duration = "00:58"),
        RingData(id = 8, title = "Jump", duration = "00:31"),
        RingData(id = 9, title = "DNA", duration = "00:46"),
        RingData(id = 10, title = "Ddaeng", duration = "00:13"),
        RingData(id = 11, title = "Begin", duration = "00:47"),
        RingData(id = 12, title = "Euphoria", duration = "00:35"),
        RingData(id = 13, title = "Fire", duration = "00:41"),
        RingData(id = 14, title = "Mikrokosmos", duration = "00:30"),
        RingData(id = 15, title = "Serendipity", duration = "00:39"),
        RingData(id = 16, title = "MIC Drop", duration = "00:38"),
        RingData(id = 17, title = "Dynamite", duration = "00:17"),
        RingData(id = 18, title = "Not Today", duration = "00:28"),
        RingData(id = 19, title = "Young Forever", duration = "00:44"),
        RingData(id = 20, title = "Butter", duration = "00:38")
    )
}

fun fetchData(context: Context, id: Int): ArrayList<String> {
    val arrayList = ArrayList<String>()
    arrayList.clear()
    arrayList.add(memberId + id)
    for (i in 0 until 4) {
        val path = memberId + id + "_" + (i + 1)
        arrayList.add(path)
    }
    arrayList.addAll(fetchMedia(context, id))
    arrayList.add(moreImage)
    return arrayList
}


private fun fetchMedia(context: Context, id: Int): List<String> {
    val arrayList = mutableListOf<String>()
    try {
        val cacheDir = context.cacheDir
        if (cacheDir.isDirectory) {
            val listFiles = cacheDir.listFiles()
            for (file in listFiles!!) {
                val fileName = file.name
                if (fileName.contains(memberId + id)) {
                    arrayList.add(file.absolutePath)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return arrayList
}

fun fetchMemberData(context: Context, id: Int): ArrayList<String> {
    val arrayList = ArrayList<String>()
    arrayList.clear()
    val wallpaperCount = context.getPrefList(SERVER_WALLPAPER)[id - 1]
    for (i in 0 until wallpaperCount) {
        val path = memberId + id + "_" + (i + 5) + imageJPG
        arrayList.add(path)
    }
    return arrayList
}

fun defaultEmoji(): Array<String> {
    return arrayOf(
        "\uD83E\uDD29",
        "\uD83D\uDE00",
        "\uD83D\uDE03",
        "\uD83D\uDE04",
        "\uD83D\uDE01",
        "\uD83D\uDE06",
        "\uD83D\uDE05",
        "\uD83E\uDD23",
        "\uD83D\uDE02",
        "\uD83D\uDE42",
        "\uD83D\uDE43",
        "\uD83D\uDE09",
        "\uD83D\uDE0A",
        "\uD83D\uDE07",
        "\uD83E\uDD70",
        "\uD83D\uDE0D",
        "\uD83D\uDE18",
        "\uD83D\uDE17",
        "☺",
        "\uD83D\uDE1A",
        "\uD83D\uDE19",
        "\uD83D\uDE0B",
        "\uD83D\uDE1B",
        "\uD83D\uDE1C",
        "\uD83E\uDD2A",
        "\uD83D\uDE1D",
        "\uD83E\uDD11",
        "\uD83E\uDD17",
        "\uD83E\uDD2D",
        "\uD83E\uDEE2",
        "\uD83E\uDEE3",
        "\uD83E\uDD2B",
        "\uD83E\uDD24"
    )
}

fun Context.fromAssets(path: String): Bitmap {
    return assets.open(path).use { stream ->
        BitmapFactory.decodeStream(stream)
    }
}

fun getRealPathFromUri(context: Context, uri: Uri): Bitmap? {
    val selectedPathVideo = getPath(context, uri)
    return try {
        val thumb = ThumbnailUtils.createVideoThumbnail(
            selectedPathVideo!!,
            MediaStore.Video.Thumbnails.MINI_KIND
        )
        thumb!!
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun bitmapFromUri(contentResolver: ContentResolver, uri: Uri): ImageBitmap? {
    val bitmap = remember {
        try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    return bitmap?.asImageBitmap()
}