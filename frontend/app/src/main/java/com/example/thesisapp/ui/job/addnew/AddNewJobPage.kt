package com.example.thesisapp.ui.job.addnew

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.ThesisButton
import com.example.thesisapp.ui.component.ThesisDivider
import com.example.thesisapp.ui.component.ThesisOutlinedTextField
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun AddNewJobPage() {
    ThesisSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add job",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4,
                color = ThesisTheme.colors.brand,
                modifier = Modifier.padding(16.dp)
            )
            ThesisDivider(thickness = 2.dp)

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            Image(
                painter = painterResource(id = R.drawable.job_placeholder),
                contentDescription = stringResource(id = R.string.login_img_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(270.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .clip(Shapes.medium)
            )

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ThesisOutlinedTextField(
                    label = "Title",
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_title),
                    value = "",
                    onValueChanged = {},
                    isError = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isTrailingIconNeeded = false,
                    modifier = Modifier.weight(3f).padding(start = 12.dp)
                )

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                ThesisOutlinedTextField(
                    label = "Price",
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_price),
                    value = "",
                    onValueChanged = {},
                    isError = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isTrailingIconNeeded = false,
                    modifier = Modifier.weight(1f).height(50.dp).padding(end = 12.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            ThesisOutlinedTextField(
                label = "Address",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_address),
                value = "",
                onValueChanged = {},
                isError = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isTrailingIconNeeded = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            ThesisOutlinedTextField(
                label = "Description",
                leadingIcon = ImageVector.vectorResource(id = R.drawable.is_desc),
                value = "",
                onValueChanged = {},
                isError = false,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isTrailingIconNeeded = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            ThesisButton(
                content = {
                    Text(
                        text = "Save",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.button,
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(150.dp),
                backgroundGradient = ThesisTheme.colors.interactiveSecondary,
                onClick = {

                }
            )
        }
    }
}

@Preview
@Composable
fun AddPreview() {
    ThesisappTheme {
        AddNewJobPage()
    }
}