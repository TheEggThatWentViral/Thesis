package com.example.thesisapp.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.HighlightCardBoxHeight
import com.example.thesisapp.ui.component.HighlightCardPadding
import com.example.thesisapp.ui.component.ThesisButton
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.component.offsetGradientVerticalBackground
import com.example.thesisapp.ui.navigation.HomeSections
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme
import kotlinx.coroutines.launch

@Composable
fun ChatPage(
    onSendMessageClicked: (String) -> Unit,
    upPress: () -> Unit,
    state: ChatViewState,
    navigateToRoute: (String) -> Unit
) {
    ThesisScaffold(
        topBar = {
            TopBar(
                upPress = upPress,
                state = state,
                navigateToRoute = navigateToRoute
            )
        }
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (messages, input) = createRefs()

            Messages(
                modifier = Modifier.constrainAs(messages) {
                    bottom.linkTo(input.top)
                },
                state = state
            )

            Input(
                modifier = Modifier.constrainAs(input) {
                    bottom.linkTo(parent.bottom)
                },
                onSendMessageClicked = onSendMessageClicked
            )
        }
    }
}

@Composable
fun TopBar(
    upPress: () -> Unit,
    state: ChatViewState,
    navigateToRoute: (String) -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ThesisTheme.colors.brand)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "",
                tint = ThesisTheme.colors.uiBackground,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { upPress() }
            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                ThesisSurface(
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.job_placeholder),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                val name = if (state.currentUser == "mike") {
                    "Kieran"
                } else {
                    "Mike"
                }

                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    color = ThesisTheme.colors.uiBackground
                )
            }

            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "",
                tint = ThesisTheme.colors.uiBackground,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { navigateToRoute(HomeSections.PROFILE.route) }
            )
        }
    }
}

@Composable
fun Input(
    modifier: Modifier,
    onSendMessageClicked: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }

    Box(modifier) {
        ThesisSurface(
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomStart = 10.dp,
                bottomEnd = 10.dp
            ),
            color = ThesisTheme.colors.chatLight,
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = ThesisTheme.colors.uiBackground,
                        backgroundColor = ThesisTheme.colors.chatDark,
                        cursorColor = ThesisTheme.colors.uiBackground,
                        unfocusedBorderColor = ThesisTheme.colors.chatDark,
                        focusedBorderColor = ThesisTheme.colors.chatDark
                    ),
                    placeholder = {
                        Text(
                            text = "Message...",
                            style = MaterialTheme.typography.caption,
                            color = ThesisTheme.colors.uiBackground
                        )
                    },
                    textStyle = MaterialTheme.typography.caption,
                    maxLines = 5,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                ThesisButton(
                    onClick = {
                        onSendMessageClicked(message)
                        message = ""
                    },
                    shape = CircleShape,
                    modifier = Modifier.size(44.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = "",
                        tint = ThesisTheme.colors.uiBackground,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Messages(
    modifier: Modifier,
    state: ChatViewState
) {
    Box(modifier) {
        val scroll = rememberScrollState(0)
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        //coroutineScope.launch { listState.animateScrollToItem(index = 7) }

        LazyColumn(
            modifier = Modifier.padding(
                start = 4.dp,
                end = 4.dp,
                bottom = 8.dp
            ),
            state = listState
        ) {
            itemsIndexed(state.messages) { index, message ->
                if (state.currentUser == "mike") {
                    if (message.senderId == "sender1") {
                        SentMessage(
                            index = index,
                            scroll = scroll.value,
                            message = message.message
                        )
                    } else {
                        ReceivedMessage(message = message.message)
                    }
                } else {
                    if (message.senderId == "sender2") {
                        SentMessage(
                            index = index,
                            scroll = scroll.value,
                            message = message.message
                        )
                    } else {
                        ReceivedMessage(message = message.message)
                    }
                }
            }
        }
    }
}

@Composable
fun ReceivedMessage(message: String) {
    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 70.dp)
    ) {
        ThesisSurface(
            shape = CircleShape,
            elevation = 5.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.job_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

        ThesisSurface(
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomStart = 5.dp,
                bottomEnd = 15.dp
            ),
            color = ThesisTheme.colors.chatDark,
            contentColor = ThesisTheme.colors.uiBackground,
            elevation = 5.dp
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun SentMessage(
    index: Int,
    scroll: Int,
    message: String
) {
    val top = index * with(LocalDensity.current) {
        (HighlightCardBoxHeight + HighlightCardPadding).toPx()
    }

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 70.dp)
    ) {
        ThesisSurface(
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomStart = 15.dp,
                bottomEnd = 5.dp
            ),
            color = ThesisTheme.colors.chatDark,
            contentColor = ThesisTheme.colors.uiBackground,
            elevation = 5.dp
        ) {
            val gradientOffset = top - (scroll / 6f)
            Box(
                modifier = Modifier.offsetGradientVerticalBackground(
                    colors = ThesisTheme.colors.interactivePrimary,
                    width = LocalContext.current.resources.displayMetrics.heightPixels.toFloat(),
                    offset = gradientOffset
                )
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ChatPagePreview() {
    ThesisappTheme {
        ChatPage(
            navigateToRoute = {},
            upPress = {},
            onSendMessageClicked = {},
            state = ChatViewState(mutableListOf(), "")
        )
    }
}

@Preview
@Composable
fun SentMessagePreview() {
    ThesisappTheme {
        SentMessage(1, 2, "This is a test preview sent message")
    }
}

@Preview
@Composable
fun ReceivedMessagePreview() {
    ThesisappTheme {
        ReceivedMessage("This is a test preview received message")
    }
}