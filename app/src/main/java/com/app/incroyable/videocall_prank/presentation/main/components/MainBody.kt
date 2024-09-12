package com.app.incroyable.videocall_prank.presentation.main.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.app.incroyable.videocall_prank.adhelper.LoadMediumNative
import com.app.incroyable.videocall_prank.core.storage.getAdBoolean
import com.app.incroyable.videocall_prank.core.storage.getAdString
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNative
import com.app.incroyable.videocall_prank.core.storage.prefAdmobNativeEnable
import com.app.incroyable.videocall_prank.core.utils.isOnline
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel

@Composable
fun MainBody(
    userViewModel: UserViewModel,
    users: List<User>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val placementId = context.getAdString(prefAdmobNative)

    val combinedList = buildList {
        addAll(users.take(3))
        if (context.getAdBoolean(prefAdmobNativeEnable) && placementId.isNotEmpty() && context.isOnline()) {
            add(Unit)
        }
        addAll(users.drop(3))
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = combinedList
        ) { item ->
            when (item) {
                is Unit -> {
                    LoadMediumNative()
                }

                is User -> {
                    MainCard(
                        userViewModel,
                        user = item
                    )
                }
            }
        }
    }
}



