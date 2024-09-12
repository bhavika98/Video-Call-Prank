package com.app.incroyable.videocall_prank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.app.incroyable.videocall_prank.adhelper.BottomBanner
import com.app.incroyable.videocall_prank.core.storage.KEY_THEME
import com.app.incroyable.videocall_prank.core.storage.getDefaultPreferences
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.USER_DATA
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModelFactory
import com.app.incroyable.videocall_prank.presentation.crop.ImagesViewModel
import com.app.incroyable.videocall_prank.presentation.updatecontact.UpdateContactScreen
import com.app.incroyable.videocall_prank.theme.ThemeSwitcherTheme

class UpdateContactActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(applicationContext)
    }
    private val viewModel: ImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent?.getSerializableExtra(USER_DATA) as User
        setContent {
            val darkTheme = remember { mutableStateOf(getDefaultPreferences(KEY_THEME) as Boolean) }
            ThemeSwitcherTheme(darkTheme = darkTheme.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        UpdateContactScreen(
                            user = user,
                            viewModel,
                            userViewModel,
                            darkTheme = darkTheme.value
                        )
                    }
                    BottomBanner()
                }
            }
        }
    }
}