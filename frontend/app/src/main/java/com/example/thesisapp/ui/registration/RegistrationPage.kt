package com.example.thesisapp.ui.registration

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.ThesisButton
import com.example.thesisapp.ui.component.ThesisOutlinedTextField
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun RegistrationPage(
    onNavigateToRoute: (String) -> Unit,
    state: RegistrationViewState,
    onUsernameValueChanged: (String) -> Unit,
    onPasswordValueChanged: (String) -> Unit,
    onPasswordAgainValueChanged: (String) -> Unit,
    onEmailValueChanged: (String) -> Unit,
    onFirstNameValueChanged: (String) -> Unit,
    onLastNameValueChanged: (String) -> Unit,
    onRegisterBtnClicked: (Context) -> Unit
) {
    ThesisSurface(
        modifier = Modifier.fillMaxSize(),
        color = ThesisTheme.colors.loginBasic
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            val lowerSectionHeight = (LocalConfiguration.current.screenHeightDp * 0.15f).dp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(lowerSectionHeight)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_label),
                    style = MaterialTheme.typography.h1,
                    color = ThesisTheme.colors.uiBackground,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            val upperSurfaceHeight = (LocalConfiguration.current.screenHeightDp * 0.9f).dp

            ThesisSurface(
                modifier = Modifier.fillMaxWidth(),
                color = ThesisTheme.colors.uiBackground,
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(upperSurfaceHeight)
                ) {
                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.first_name),
                        leadingIcon = Icons.Outlined.Face,
                        value = state.firstname,
                        onValueChanged = onFirstNameValueChanged,
                        isError = state.firstnameError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isTrailingIconNeeded = false
                    )

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.last_name),
                        leadingIcon = Icons.Outlined.Face,
                        value = state.lastname,
                        onValueChanged = onLastNameValueChanged,
                        isError = state.lastnameError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isTrailingIconNeeded = false
                    )

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.email),
                        leadingIcon = Icons.Outlined.Email,
                        value = state.email,
                        onValueChanged = onEmailValueChanged,
                        isError = state.emailError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        isTrailingIconNeeded = false
                    )

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.username),
                        leadingIcon = Icons.Outlined.AccountCircle,
                        value = state.username,
                        onValueChanged = onUsernameValueChanged,
                        isError = state.usernameError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        isTrailingIconNeeded = false
                    )

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.password),
                        leadingIcon = Icons.Outlined.Lock,
                        value = state.password,
                        onValueChanged = onPasswordValueChanged,
                        isError = state.passwordError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isTrailingIconNeeded = true
                    )

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    ThesisOutlinedTextField(
                        label = stringResource(id = R.string.password_agian),
                        leadingIcon = Icons.Outlined.Lock,
                        value = state.passwordAgain,
                        onValueChanged = onPasswordAgainValueChanged,
                        isError = state.passwordError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isTrailingIconNeeded = true
                    )

                    Spacer(modifier = Modifier.padding(vertical = 16.dp))

                    val context = LocalContext.current

                    ThesisButton(
                        content = {
                            Text(
                                text = stringResource(id = R.string.register_btn),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.button,
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(150.dp),
                        backgroundGradient = ThesisTheme.colors.interactiveSecondary,
                        onClick = { onRegisterBtnClicked(context) }
                    )
                }
            }
        }
    }

}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProfilePreview() {
    ThesisappTheme {
        RegistrationPage(
            onNavigateToRoute = { "" },
            state = RegistrationViewState(),
            onPasswordValueChanged = {},
            onUsernameValueChanged = {},
            onEmailValueChanged = {},
            onFirstNameValueChanged = {},
            onLastNameValueChanged = {},
            onPasswordAgainValueChanged = {},
            onRegisterBtnClicked = {}
        )
    }
}