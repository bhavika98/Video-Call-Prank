package com.app.incroyable.videocall_prank.presentation.updatecontact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mr0xf00.easycrop.ui.ImageCropperDialog
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.presentation.addcontact.components.AddContactBody
import com.app.incroyable.videocall_prank.presentation.common.CustomToolbar
import com.app.incroyable.videocall_prank.presentation.crop.ImagesViewModel
import com.app.incroyable.videocall_prank.presentation.crop.ui.CropErrorDialog
import com.app.incroyable.videocall_prank.presentation.crop.ui.LoadingDialog
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.presentation.updatecontact.components.UpdateContactBody
import com.app.incroyable.videocall_prank.theme.customBackground
import com.app.incroyable.videocall_prank.theme.getTheme

@Composable
fun UpdateContactScreen(
    user: User,
    viewModel: ImagesViewModel,
    userViewModel: UserViewModel,
    darkTheme: Boolean
) {
    val scrollState = rememberScrollState()
    val darkThemeState = remember { mutableStateOf(darkTheme) }

    val cropState = viewModel.imageCropper.cropState
    val loadingStatus = viewModel.imageCropper.loadingStatus
    val selectedImage by viewModel.selectedImage.collectAsState()
    val cropError = viewModel.cropError.collectAsState().value

    CompositionLocalProvider(LocalDarkTheme provides darkThemeState.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(getTheme(LocalDarkTheme.current, customBackground))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(getTheme(LocalDarkTheme.current, customBackground))
            ) {
                CustomToolbar(
                    darkTheme = darkThemeState.value,
                    stringResource(R.string.update_contact)
                )
                Spacer(modifier = Modifier.height(4.dp))
                UpdateContactBodyWithScrollbar(
                    user = user,
                    selectedImage = selectedImage,
                    viewModel,
                    userViewModel
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            if (cropState != null) {
                ImageCropperDialog(
                    darkTheme = darkTheme,
                    state = cropState
                )
            }

            if (cropState == null && loadingStatus != null) {
                LoadingDialog(status = loadingStatus)
            }

            cropError?.let { error ->
                CropErrorDialog(error, onDismiss = { viewModel.cropErrorShown() })
            }
        }
    }
}

@Composable
fun UpdateContactBodyWithScrollbar(
    user: User,
    selectedImage: ImageBitmap?,
    viewModel: ImagesViewModel,
    userViewModel: UserViewModel
) {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)

    ) {
        UpdateContactBody(
            user = user,
            selectedImage = selectedImage,
            viewModel,
            userViewModel
        )
    }
}




