package com.app.incroyable.videocall_prank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.app.incroyable.videocall_prank.core.storage.KEY_MEMBERS_ADDED
import com.app.incroyable.videocall_prank.core.storage.KEY_THEME
import com.app.incroyable.videocall_prank.core.storage.SAVED_COUNTER
import com.app.incroyable.videocall_prank.core.storage.defaultMembers
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.core.storage.setDefaultPreferences
import com.app.incroyable.videocall_prank.core.storage.setPrefList
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModelFactory
import com.app.incroyable.videocall_prank.presentation.main.MainScreen
import com.app.incroyable.videocall_prank.theme.ThemeSwitcherTheme

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isMembersAdded = getDefaultPreferences(KEY_MEMBERS_ADDED) as Boolean
        if (!isMembersAdded) {
            val defaultValue = listOf(0, 0, 0, 0, 0, 0, 0)
            setPrefList(defaultValue, SAVED_COUNTER)
            userViewModel.addMembers(defaultMembers())
            setDefaultPreferences(KEY_MEMBERS_ADDED, true)
        }
        setContent {
            val darkTheme = remember { mutableStateOf(getDefaultPreferences(KEY_THEME) as Boolean) }
            ThemeSwitcherTheme(darkTheme = darkTheme.value) {
                MainScreen(
                    userViewModel,
                    darkTheme = darkTheme.value,
                    onThemeUpdated = {
                        darkTheme.value = !darkTheme.value
                        setDefaultPreferences(KEY_THEME, darkTheme.value)
                    }
                )
            }
        }
    }
}