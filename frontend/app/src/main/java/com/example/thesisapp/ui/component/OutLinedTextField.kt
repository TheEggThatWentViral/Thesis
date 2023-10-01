package com.example.thesisapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme

@Composable
fun ThesisOutlinedTextField(
    label: String,
    leadingIcon: ImageVector,
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    isTrailingIconNeeded: Boolean
) {

    val isVisible = remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        textStyle = MaterialTheme.typography.subtitle1,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = stringResource(
                    id = R.string.login_username_icon
                ),
                modifier = Modifier.padding(start = 12.dp)
            )
        },
        trailingIcon = {
            if (isTrailingIconNeeded) {
                val iconId = if (isVisible.value) {
                    R.drawable.ic_visible
                } else {
                    R.drawable.ic_non_visible
                }
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = stringResource(
                        id = R.string.login_username_icon
                    ),
                    tint = ThesisTheme.colors.loginBasic,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { isVisible.value = !isVisible.value }
                )
            }
        },
        visualTransformation = if (isVisible.value && isTrailingIconNeeded) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
       },
        keyboardOptions = keyboardOptions,
        isError = isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = ThesisTheme.colors.loginBasic,
            focusedLabelColor = ThesisTheme.colors.loginFocus,
            textColor = ThesisTheme.colors.loginBasic,
            backgroundColor = ThesisTheme.colors.uiBackground,
            cursorColor = ThesisTheme.colors.loginBasic,
            leadingIconColor = ThesisTheme.colors.loginBasic,
            placeholderColor = ThesisTheme.colors.loginBasic,
            unfocusedBorderColor = ThesisTheme.colors.loginBasic,
            focusedBorderColor = ThesisTheme.colors.loginFocus,
            errorBorderColor = ThesisTheme.colors.error
        ),
        shape = Shapes.medium
    )
}