package com.example.thesisapp.ui.login

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.ThesisButton
import com.example.thesisapp.ui.component.ThesisButtonGradient
import com.example.thesisapp.ui.component.ThesisOutlinedTextField
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.navigation.AuthenticationSections
import com.example.thesisapp.ui.navigation.HomeSections
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme
import de.palm.composestateevents.EventEffect

@Composable
fun LoginPage(
    onNavigateToRoute: (String) -> Unit,
    state: LoginViewState,
    onUsernameValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onLoginBtnClicked: (Context) -> Unit,
    onRememberMeToggled: (Boolean) -> Unit,
    onConsumedLoginSuccess: () -> Unit,
    onConsumedLoginError: () -> Unit
) {

    EventEffect(
        event = state.loginSuccess,
        onConsumed = onConsumedLoginSuccess
    ) {
        onNavigateToRoute(HomeSections.FEED.route)
    }

    EventEffect(
        event = state.loginError,
        onConsumed = onConsumedLoginError
    ) {

    }

    ThesisSurface(
        modifier = Modifier.fillMaxSize(),
        color = ThesisTheme.colors.loginBasic
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            UpperSurface(
                onNavigateToRoute = onNavigateToRoute,
                state = state,
                onUsernameValueChanged = onUsernameValueChanged,
                onPasswordValueChanged = onPasswordValueChanged,
                onLoginBtnClicked = onLoginBtnClicked,
                onRememberMeToggled = onRememberMeToggled
            )

            LowerSurface()
        }
    }
}

@Composable
fun UpperSurface(
    onNavigateToRoute: (String) -> Unit,
    state: LoginViewState,
    onUsernameValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onLoginBtnClicked: (Context) -> Unit,
    onRememberMeToggled: (Boolean) -> Unit
) {

    val upperSurfaceHeight = (LocalConfiguration.current.screenHeightDp * 0.9f).dp

    ThesisSurface(
        modifier = Modifier
            .height(upperSurfaceHeight)
            .fillMaxWidth(),
        color = ThesisTheme.colors.uiBackground,
        elevation = 10.dp,
        shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
    ) {

        Box(
            modifier = Modifier
                .height(upperSurfaceHeight)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.login_img),
                    contentDescription = stringResource(id = R.string.login_img_desc),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(270.dp)
                        .clip(Shapes.medium)
                        .align(CenterHorizontally)
                )

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                ThesisOutlinedTextField(
                    label = stringResource(id = R.string.username),
                    leadingIcon = Icons.Outlined.AccountCircle,
                    value = state.username,
                    onValueChanged = onUsernameValueChanged,
                    isError = state.usernameError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isTrailingIconNeeded = false
                )

                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                ThesisOutlinedTextField(
                    label = stringResource(id = R.string.password),
                    leadingIcon = Icons.Outlined.Lock,
                    value = state.password,
                    onValueChanged = onPasswordValueChanged,
                    isError = state.passwordError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isTrailingIconNeeded = true
                )

                RememberMeSection(
                    state = state,
                    onRememberMeToggled = onRememberMeToggled
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                val context = LocalContext.current

                ThesisButton(
                    content = {
                        Text(
                            text = stringResource(id = R.string.sign_in_btn),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.button,
                        )
                    },
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .width(150.dp),
                    backgroundGradient = ThesisTheme.colors.interactiveSecondary,
                    onClick = {
                        onLoginBtnClicked(context)
                    }
                )

                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                ThesisButtonGradient(
                    label = stringResource(id = R.string.sign_up_btn),
                    onClick = { onNavigateToRoute(AuthenticationSections.REGISTRATION.route) },
                    modifier = Modifier.align(CenterHorizontally)
                )

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                Text(
                    text = stringResource(id = R.string.forgot_password_label),
                    style = MaterialTheme.typography.caption,
                    color = ThesisTheme.colors.textHelp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun RememberMeSection(
    state: LoginViewState,
    onRememberMeToggled: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(id = R.string.remember_me_label),
            style = MaterialTheme.typography.caption,
            color = ThesisTheme.colors.textHelp
        )

        Switch(
            checked = state.rememberMe,
            onCheckedChange = onRememberMeToggled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = ThesisTheme.colors.checkFocus,
                checkedTrackColor = ThesisTheme.colors.checkFocus,
                uncheckedThumbColor = ThesisTheme.colors.checkBasic,
                uncheckedTrackColor = ThesisTheme.colors.checkBasic
            )
        )
    }
}

@Composable
fun LowerSurface() {
    val lowerSectionHeight = (LocalConfiguration.current.screenHeightDp * 0.1f).dp

    Row(
        modifier = Modifier
            .height(lowerSectionHeight)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_icon),
            modifier = Modifier
                .size(45.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = Shapes.small
                ),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.login_google_icon)
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Image(
            painter = painterResource(id = R.drawable.facebook_icon),
            modifier = Modifier
                .size(45.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = Shapes.small
                ),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.login_facebook_icon)
        )

        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        Image(
            painter = painterResource(id = R.drawable.twitter_icon),
            modifier = Modifier
                .size(45.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = Shapes.small
                ),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.login_twitter_icon)
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfilePreview() {
    ThesisappTheme {
        LoginPage(
            onNavigateToRoute = {},
            state = LoginViewState(),
            onUsernameValueChanged = {},
            onPasswordValueChanged = {},
            onLoginBtnClicked = {},
            onRememberMeToggled = {},
            onConsumedLoginError = {},
            onConsumedLoginSuccess = {}
        )
    }
}