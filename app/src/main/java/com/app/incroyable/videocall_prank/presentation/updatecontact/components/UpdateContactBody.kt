package com.app.incroyable.videocall_prank.presentation.updatecontact.components

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr0xf00.easycrop.rememberImagePicker
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.dialog.EmojiDialog
import com.app.incroyable.videocall_prank.core.storage.MyStaticVariable
import com.app.incroyable.videocall_prank.core.storage.bitmapFromUri
import com.app.incroyable.videocall_prank.core.storage.getRealPathFromUri
import com.app.incroyable.videocall_prank.core.utils.toastMsg
import com.app.incroyable.videocall_prank.core.widgets.CustomSquareBox
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.UPDATED_USER_DATA
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.presentation.common.findActivity
import com.app.incroyable.videocall_prank.presentation.crop.ImagesViewModel
import com.app.incroyable.videocall_prank.presentation.main.LocalDarkTheme
import com.app.incroyable.videocall_prank.theme.customButton
import com.app.incroyable.videocall_prank.theme.customCardMain
import com.app.incroyable.videocall_prank.theme.customTextSubtitle
import com.app.incroyable.videocall_prank.theme.customTextTitle
import com.app.incroyable.videocall_prank.theme.customTextTitleOpposite
import com.app.incroyable.videocall_prank.theme.fontVarela
import com.app.incroyable.videocall_prank.theme.getTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun UpdateContactBody(
    user: User,
    selectedImage: ImageBitmap?,
    viewModel: ImagesViewModel,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(user.image.let { Uri.parse(it) }) }
    var videoUri by remember { mutableStateOf<Uri?>(user.video.let { Uri.parse(it) }) }

    var firstImage by remember { mutableStateOf(true) }

    val imagePicker = rememberImagePicker(onImage =
    { uri ->
        viewModel.setSelectedImage(uri)
    })

    val videoLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            videoUri = uri
        }

    val focusedBorderColor = Color(0xFFAAE9E6)
    val unfocusedBorderColor = Color.Transparent

    val nameState = remember { mutableStateOf(user.name) }
    val isFocusedName = remember { mutableStateOf(false) }
    val borderNameColor = derivedStateOf {
        if (isFocusedName.value) focusedBorderColor else unfocusedBorderColor
    }

    val contactState = remember { mutableStateOf(user.contact) }
    val isFocusedContact = remember { mutableStateOf(false) }
    val borderContactColor = derivedStateOf {
        if (isFocusedContact.value) focusedBorderColor else unfocusedBorderColor
    }

    val emojiState = remember { mutableStateOf(user.emoji) }
    val showDialog = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.name),
                        fontFamily = fontVarela,
                        fontSize = 14.sp,
                        color = getTheme(LocalDarkTheme.current, customTextTitle)
                    )
                    BasicTextField(
                        cursorBrush = SolidColor(getTheme(LocalDarkTheme.current, customTextTitle)),
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        singleLine = true,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                            .background(
                                color = getTheme(LocalDarkTheme.current, customCardMain),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .onFocusChanged { isFocusedName.value = it.isFocused },
                        textStyle = TextStyle(
                            fontFamily = fontVarela,
                            color = getTheme(LocalDarkTheme.current, customTextTitle)
                        ),
                        decorationBox = { innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 2.dp,
                                        color = borderNameColor.value,
                                        shape = RoundedCornerShape(size = 10.dp)
                                    )
                                    .padding(all = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Spacer(modifier = Modifier.width(width = 6.dp))
                                Box {
                                    if (nameState.value.isEmpty() && !isFocusedName.value) {
                                        Text(
                                            text = stringResource(id = R.string.hint_name),
                                            color = getTheme(
                                                LocalDarkTheme.current,
                                                customTextSubtitle
                                            ),
                                            textAlign = TextAlign.Center,
                                            fontFamily = fontVarela,
                                            fontSize = 13.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.reaction),
                        color = getTheme(LocalDarkTheme.current, customTextTitle),
                        fontFamily = fontVarela,
                        fontSize = 14.sp,
                    )
                    Card(
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .padding(top = 5.dp),
                        backgroundColor = getTheme(LocalDarkTheme.current, customCardMain),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    showDialog.value = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = emojiState.value,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(id = R.string.mobile_no),
                color = getTheme(LocalDarkTheme.current, customTextTitle),
                fontFamily = fontVarela,
                fontSize = 14.sp,
            )
            BasicTextField(
                cursorBrush = SolidColor(getTheme(LocalDarkTheme.current, customTextTitle)),
                value = contactState.value,
                onValueChange = { contactState.value = it },
                singleLine = true,
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .background(
                        color = getTheme(LocalDarkTheme.current, customCardMain),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .onFocusChanged { isFocusedContact.value = it.isFocused },
                textStyle = TextStyle(
                    fontFamily = fontVarela,
                    color = getTheme(LocalDarkTheme.current, customTextTitle)
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                color = borderContactColor.value,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .padding(all = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(width = 6.dp))
                        Box {
                            if (contactState.value.isEmpty() && !isFocusedContact.value) {
                                Text(
                                    text = stringResource(id = R.string.hint_mobile),
                                    color = getTheme(LocalDarkTheme.current, customTextSubtitle),
                                    textAlign = TextAlign.Center,
                                    fontFamily = fontVarela,
                                    fontSize = 13.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.pick_image),
                        color = getTheme(LocalDarkTheme.current, customTextTitle),
                        fontFamily = fontVarela,
                        fontSize = 14.sp,
                    )
                    CustomSquareBox {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 5.dp),
                            backgroundColor = getTheme(LocalDarkTheme.current, customCardMain),
                            elevation = 0.dp,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        imagePicker.pick()
//                                    imageLauncher.launch("image/*")
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (selectedImage != null) {
                                    firstImage = false
                                    imageUri = if (MyStaticVariable.imgUri != null)
                                        MyStaticVariable.imgUri
                                    else {
                                        null
                                    }
                                }
                                if (imageUri != null) {
                                    if (firstImage) {
                                        val imageBitmap = bitmapFromUri(
                                            LocalContext.current.contentResolver,
                                            Uri.parse(user.image)
                                        )
                                        Image(
                                            bitmap = imageBitmap!!,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Image(
                                            bitmap = selectedImage!!,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_remove),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(35.dp)
                                            .height(35.dp)
                                            .padding(5.dp)
                                            .align(Alignment.TopEnd)
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                MyStaticVariable.imgUri = null
                                                imageUri = null
                                                viewModel.clearSelectedImage()
                                            },
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_image),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp),
                                            contentScale = ContentScale.Crop,
                                            colorFilter = ColorFilter.tint(
                                                getTheme(
                                                    LocalDarkTheme.current,
                                                    customTextSubtitle
                                                )
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = stringResource(id = R.string.add_image),
                                            color = getTheme(
                                                LocalDarkTheme.current,
                                                customTextSubtitle
                                            ),
                                            fontFamily = fontVarela,
                                            fontSize = 12.sp,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.pick_video),
                        color = getTheme(LocalDarkTheme.current, customTextTitle),
                        fontFamily = fontVarela,
                        fontSize = 14.sp,
                    )
                    CustomSquareBox {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 5.dp),
                            backgroundColor = getTheme(LocalDarkTheme.current, customCardMain),
                            elevation = 0.dp,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        videoLauncher.launch("video/*")
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (videoUri == null) {
                                        Image(
                                            painter = painterResource(id = R.drawable.icon_video),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp),
                                            contentScale = ContentScale.Crop,
                                            colorFilter = ColorFilter.tint(
                                                getTheme(
                                                    LocalDarkTheme.current,
                                                    customTextSubtitle
                                                )
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Text(
                                            text = stringResource(id = R.string.add_video),
                                            color = getTheme(
                                                LocalDarkTheme.current,
                                                customTextSubtitle
                                            ),
                                            fontFamily = fontVarela,
                                            fontSize = 12.sp,
                                        )
                                    }
                                }
                                if (videoUri != null) {
                                    val thumbnailBitmap =
                                        getRealPathFromUri(LocalContext.current, videoUri!!)
                                    Image(
                                        bitmap = thumbnailBitmap!!.asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(),
                                        contentScale = ContentScale.Crop
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_remove),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(35.dp)
                                            .height(35.dp)
                                            .padding(5.dp)
                                            .align(Alignment.TopEnd)
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                videoUri = null
                                            },
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column(modifier = Modifier.wrapContentWidth()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 5.dp),
                    backgroundColor = getTheme(LocalDarkTheme.current, customButton),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                if (nameState.value.isEmpty() || contactState.value.isEmpty() || imageUri == null || videoUri == null) {
                                    if (nameState.value.isEmpty())
                                        context.toastMsg(context.getString(R.string.please_enter_name))
                                    else if (contactState.value.isEmpty())
                                        context.toastMsg(context.getString(R.string.please_enter_mobile_no))
                                    else if (imageUri == null)
                                        context.toastMsg(context.getString(R.string.please_pick_image))
                                    else if (videoUri == null)
                                        context.toastMsg(context.getString(R.string.please_pick_video))
                                } else {
                                    val userData = User(
                                        user.id,
                                        user.type,
                                        nameState.value,
                                        contactState.value,
                                        imageUri.toString(),
                                        videoUri.toString(),
                                        emojiState.value
                                    )
                                    userViewModel.updateUser(
                                        userData
                                    )
                                    context.toastMsg(context.getString(R.string.contact_updated_successfully))
                                    val resultIntent = Intent().apply {
                                        putExtra(UPDATED_USER_DATA, userData)
                                    }
                                    context
                                        .findActivity()?.setResult(Activity.RESULT_OK, resultIntent)
                                    context
                                        .findActivity()
                                        ?.finish()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.update),
                            color = getTheme(LocalDarkTheme.current, customTextTitleOpposite),
                            textAlign = TextAlign.Center,
                            fontFamily = fontVarela,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = TextStyle(color = Color.Black)
                        )
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        EmojiDialog(
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false },
            onItemClick = { emoji ->
                emojiState.value = emoji
                showDialog.value = false
            }
        )
    }
}


