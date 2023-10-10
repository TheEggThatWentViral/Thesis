package com.example.thesisapp.ui.job.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.ThesisButton
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.component.diagonalGradientBorder
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun JobDetailPage(
    jobId: Long,
    upPress: () -> Unit
) {
    val jobList = advertisedJobs.filter { job -> job.id == jobId }
    val job = jobList.first()

    val scroll = rememberScrollState(0)

    ThesisScaffold {
        Column(modifier = Modifier.verticalScroll(scroll)) {
            Image(
                painter = painterResource(id = R.drawable.job_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = job.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4,
                    color = ThesisTheme.colors.textSecondary,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = job.price.toString() + " $",
                    style = MaterialTheme.typography.h4 ,
                    color = ThesisTheme.colors.checkFocus,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    tint = ThesisTheme.colors.brand,
                    contentDescription = stringResource(R.string.detail_location_icon),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(16.dp)
                        .diagonalGradientBorder(
                            colors = ThesisTheme.colors.interactiveSecondary,
                            shape = CircleShape
                        )
                )

                Text(
                    text = job.address.toString(),
                    style = MaterialTheme.typography.body1,
                    color = ThesisTheme.colors.textHelp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Row {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        tint = ThesisTheme.colors.brand,
                        contentDescription = stringResource(R.string.detail_time_icon),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(16.dp)
                            .diagonalGradientBorder(
                                colors = ThesisTheme.colors.interactiveSecondary,
                                shape = CircleShape
                            )
                    )

                    Text(
                        text = job.time,
                        style = MaterialTheme.typography.body1,
                        color = ThesisTheme.colors.textHelp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = stringResource(id = R.string.details_label),
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = job.description,
                style = MaterialTheme.typography.body2,
                color = ThesisTheme.colors.textHelp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            ThesisButton(
                content = {
                    Text(
                        text = stringResource(id = R.string.apply_btn),
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
fun FeedPreview() {
    ThesisappTheme {
        JobDetailPage(
            jobId = 1L,
            upPress = {}
        )
    }
}