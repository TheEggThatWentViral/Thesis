package com.example.thesisapp.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.ui.navigation.AuthenticationSections
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun ThesisButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ButtonShape,
    border: BorderStroke? = null,
    backgroundGradient: List<Color> = ThesisTheme.colors.interactivePrimary,
    disabledBackgroundGradient: List<Color> = ThesisTheme.colors.interactiveSecondary,
    contentColor: Color = ThesisTheme.colors.textInteractive,
    disabledContentColor: Color = ThesisTheme.colors.textHelp,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    ThesisSurface(
        shape = shape,
        color = Color.Transparent,
        contentColor = if (enabled) contentColor else disabledContentColor,
        border = border,
        modifier = modifier
            .clip(shape)
            .background(
                Brush.horizontalGradient(
                    colors = if (enabled) backgroundGradient else disabledBackgroundGradient
                )
            )
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        ProvideTextStyle(
            value = MaterialTheme.typography.button
        ) {
            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = ButtonDefaults.MinHeight
                    )
                    .indication(interactionSource, rememberRipple())
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Composable
fun ThesisButtonGradient(
    modifier: Modifier = Modifier,
    label: String = "Demo",
    onClick: () -> Unit
) {
    ThesisSurface(
        color = ThesisTheme.colors.uiBackground,
        contentColor = ThesisTheme.colors.loginBasic,
        shape = Shapes.small,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .diagonalGradientBorder(
                    colors = ThesisTheme.colors.interactiveSecondary,
                    shape = Shapes.small
                )
                .height(ButtonDefaults.MinHeight)
                .width(150.dp)
                .clickable {
                    onClick()
                }
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.button,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 6.dp
                    )
                    .align(Alignment.Center)
            )
        }
    }
}


private val ButtonShape = RoundedCornerShape(percent = 50)

@Preview("default", "round")
@Preview("dark theme", "round", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", "round", fontScale = 2f)
@Composable
private fun ButtonPreview() {
    ThesisappTheme {
        ThesisButton(onClick = {}) {
            Text(text = "Demo")
        }
    }
}

@Preview
@Composable
private fun ButtonGradientPreview() {
    ThesisappTheme {
        ThesisButtonGradient(onClick = {})
    }
}

@Preview("default", "rectangle")
@Preview("dark theme", "rectangle", uiMode = UI_MODE_NIGHT_YES)
@Preview("large font", "rectangle", fontScale = 2f)
@Composable
private fun RectangleButtonPreview() {
    ThesisappTheme {
        ThesisButton(
            onClick = {}, shape = RectangleShape
        ) {
            Text(text = "Demo")
        }
    }
}
