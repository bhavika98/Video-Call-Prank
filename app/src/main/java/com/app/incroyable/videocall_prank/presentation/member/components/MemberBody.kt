package com.app.incroyable.videocall_prank.presentation.member.components

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.incroyable.videocall_prank.R
import com.app.incroyable.videocall_prank.core.dialog.EmojiDialog
import com.app.incroyable.videocall_prank.core.storage.fetchData
import com.app.incroyable.videocall_prank.core.utils.toastMsg
import com.app.incroyable.videocall_prank.core.viewmodels.ImageViewModel
import com.app.incroyable.videocall_prank.database.model.User
import com.app.incroyable.videocall_prank.database.network.UPDATED_USER_DATA
import com.app.incroyable.videocall_prank.database.viewmodel.UserViewModel
import com.app.incroyable.videocall_prank.presentation.common.findActivity
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
fun MemberBody(
    user: User,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current

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

    var selectedIndex by remember { mutableStateOf(user.image) }
    val onItemClick = { index: String ->
        selectedIndex = index
    }

    ImageViewModel.myPrefs = ImageViewModel.myPrefs.copy(
        dataList = fetchData(context, user.id)
    )

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
            Row(modifier = Modifier.weight(1f)) {
                Column {
                    Text(
                        text = stringResource(id = R.string.select_image),
                        color = getTheme(LocalDarkTheme.current, customTextTitle),
                        fontFamily = fontVarela,
                        fontSize = 14.sp,
                    )
                    LazyVerticalGrid(
                        modifier = Modifier.padding(top = 5.dp),
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(
                            items = ImageViewModel.myPrefs.dataList
                        ) { index ->
                            MemberCard(
                                userId = user.id,
                                index = index,
                                selected = selectedIndex == index,
                                onClick = onItemClick,
                                image = index
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            ) {
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
                                if (nameState.value.isEmpty() || contactState.value.isEmpty()) {
                                    if (nameState.value.isEmpty())
                                        context.toastMsg(context.getString(R.string.please_enter_name))
                                    else if (contactState.value.isEmpty())
                                        context.toastMsg(context.getString(R.string.please_enter_mobile_no))
                                } else {
                                    val userData = User(
                                        user.id,
                                        user.type,
                                        nameState.value,
                                        contactState.value,
                                        selectedIndex,
                                        user.video,
                                        emojiState.value
                                    )
                                    userViewModel.updateUser(
                                        userData
                                    )
                                    context.toastMsg(context.getString(R.string.member_updated_successfully))
                                    val resultIntent = Intent().apply {
                                        putExtra(UPDATED_USER_DATA, userData)
                                    }
                                    context
                                        .findActivity()
                                        ?.setResult(Activity.RESULT_OK, resultIntent)
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


