package com.app.incroyable.videocall_prank.core.storage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

private const val PREFERENCES = "PREFERENCES"
const val KEY_THEME = "theme"
const val KEY_MEMBERS_ADDED = "members_added"
const val KEY_CALL_THEME = "call_theme"
const val KEY_CALL_RING = "call_ring"
const val KEY_CALL_VIBRATE = "call_vibrate"

const val SERVER_WALLPAPER = "server_wallpaper"
const val SERVER_VIDEO = "server_video"
const val SAVED_COUNTER = "saved_counter"

fun Context.setDefaultPreferences(key: String, value: Any) {
    val sharedPreferences: SharedPreferences =
        getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    when (value) {
        is Int -> {
            editor.putInt(key, value)
        }

        is String -> {
            editor.putString(key, value)
        }

        is Boolean -> {
            editor.putBoolean(key, value)
        }
    }
    editor.apply()
}

fun Context.getDefaultPreferences(
    key: String
): Any {
    val sharedPreferences: SharedPreferences =
        getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    val result: Any = when (key) {

        KEY_THEME, KEY_MEMBERS_ADDED -> {
            sharedPreferences.getBoolean(
                key,
                false
            )
        }

        KEY_CALL_VIBRATE -> {
            sharedPreferences.getBoolean(
                key,
                true
            )
        }

        KEY_CALL_THEME, KEY_CALL_RING -> {
            sharedPreferences.getInt(
                key,
                1
            )
        }

        else -> {}
    }
    return result
}


fun Context.setPrefList(intList: List<Int>, key: String) {
    val sharedPreferences = this.getSharedPreferences(
        PREFERENCES,
        Activity.MODE_PRIVATE
    )
    val editor = sharedPreferences.edit()

    val gson = Gson()
    val json = gson.toJson(intList)

    editor.putString(key, json)
    editor.apply()
}

fun Context.getPrefList(key: String): List<Int> {
    val sharedPreferences = this.getSharedPreferences(
        PREFERENCES,
        Activity.MODE_PRIVATE
    )
    val json = sharedPreferences.getString(key, "")

    val gson = Gson()
    val listType = object : TypeToken<List<Int>>() {}.type
    return gson.fromJson(json, listType)
}

fun Context.updatePrefList(position: Int, newValue: Int) {
    val intList = getPrefList(SAVED_COUNTER).toMutableList()
    if (position >= 0 && position < intList.size) {
        intList[position] = newValue
        setPrefList(intList, SAVED_COUNTER)
    }
}
