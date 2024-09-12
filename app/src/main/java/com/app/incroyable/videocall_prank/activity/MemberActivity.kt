package com.app.incroyable.videocall_prank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.app.incroyable.videocall_prank.core.storage.KEY_THEME
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.USER_DATA
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModelFactory
import com.app.incroyable.videocall_prank.presentation.member.MemberScreen
import com.app.incroyable.videocall_prank.theme.ThemeSwitcherTheme

class MemberActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent?.getSerializableExtra(USER_DATA) as User
        setContent {
            val darkTheme = remember { mutableStateOf(getDefaultPreferences(KEY_THEME) as Boolean) }
            ThemeSwitcherTheme(darkTheme = darkTheme.value) {
                MemberScreen(
                    user = user,
                    userViewModel,
                    darkTheme = darkTheme.value
                )
            }
        }
    }
}