package com.example.thesisapp.ui.job.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.ui.component.HighlightCardBoxHeight
import com.example.thesisapp.ui.component.HighlightCardPadding
import com.example.thesisapp.ui.component.HighlightPersonItemWide
import com.example.thesisapp.ui.component.Jobs
import com.example.thesisapp.ui.component.ThesisButtonGradient
import com.example.thesisapp.ui.component.ThesisCard
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.component.users
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun StateDetailsPage(
    jobId: Long
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
                    style = MaterialTheme.typography.h4,
                    color = ThesisTheme.colors.textPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            val stateLabelId = when (job.jobState) {
                JobState.ACTIVE -> R.string.active_label
                JobState.INACTIVE -> R.string.approval_label
                JobState.APPROVED -> R.string.inactive_label
                JobState.DONE -> R.string.done_label
            }

            val colorState = when (job.jobState) {
                JobState.ACTIVE -> ThesisTheme.colors.orange
                JobState.INACTIVE -> ThesisTheme.colors.checkFocus
                JobState.APPROVED -> ThesisTheme.colors.green
                JobState.DONE -> ThesisTheme.colors.green
            }

            Text(
                text = stringResource(id = stateLabelId),
                style = MaterialTheme.typography.h6,
                color = colorState,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                ThesisCard(
                    elevation = 2.dp,
                    modifier = Modifier
                        .height(140.dp)
                        .weight(2f)
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.time_label),
                                style = MaterialTheme.typography.h6,
                                color = ThesisTheme.colors.textSecondary
                            )
                            Spacer(modifier = Modifier.padding(vertical = 4.dp))
                            Text(
                                text = job.time,
                                style = MaterialTheme.typography.h5,
                                color = ThesisTheme.colors.textHelp
                            )
                        }
                    }
                }

                ThesisCard(
                    elevation = 2.dp,
                    modifier = Modifier
                        .height(140.dp)
                        .weight(3f)
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.address_label),
                                    style = MaterialTheme.typography.h6,
                                    color = ThesisTheme.colors.textSecondary
                                )
                                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                                Text(
                                    text = job.address.toString(),
                                    style = MaterialTheme.typography.h5,
                                    color = ThesisTheme.colors.textHelp
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_forward),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            ThesisCard(
                elevation = 2.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = stringResource(id = R.string.details_label),
                        style = MaterialTheme.typography.h6,
                        color = ThesisTheme.colors.textSecondary
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = job.description,
                        style = MaterialTheme.typography.body2,
                        color = ThesisTheme.colors.textHelp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.height(200.dp)
                    )
                    Text(
                        text = "MORE",
                        style = MaterialTheme.typography.subtitle1,
                        color = ThesisTheme.colors.textPrimary,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            ActiveLowerSection(
                isMyJob = false,
                scroll = scroll
            )
        }
    }
}

@Composable
fun ActiveLowerSection(
    isMyJob: Boolean,
    scroll: ScrollState
) {
    val labelId = if (isMyJob) {
        R.string.applicants_label
    } else {
        R.string.publisher_label
    }

    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.h6,
        color = ThesisTheme.colors.textSecondary,
        modifier = Modifier.padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.padding(vertical = 4.dp))

    if (isMyJob) {
        Jobs(
            jobs = advertisedJobs,
            onJobClick = {}
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ThesisButtonGradient(
                label = stringResource(id = R.string.delete_btn),
                onClick = { },
                contentColor = ThesisTheme.colors.checkFocus,
                borderColors = listOf(ThesisTheme.colors.checkFocus, ThesisTheme.colors.checkFocus)
            )
            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
            ThesisButtonGradient(
                label = stringResource(id = R.string.edit_btn),
                onClick = { }
            )
        }
    } else {
        val user = users.first()
        val gradientHeight = with(LocalDensity.current) {
            (6 * (HighlightCardBoxHeight + HighlightCardPadding).toPx())
        }

        HighlightPersonItemWide(
            user = user,
            onUserClicked = {},
            index = 2,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientHeight = gradientHeight,
            scroll = scroll.value
        )
    }
}

@Composable
fun DoneLowerSection(
    isMyJob: Boolean
) {

}

@Preview
@Composable
fun StateDetailsPagePreview() {
    ThesisappTheme {
        StateDetailsPage(
            jobId = 1L
        )
    }
}