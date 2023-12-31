package com.example.thesisapp.ui.job.management

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.HighlightCardBoxHeight
import com.example.thesisapp.ui.component.HighlightCardPadding
import com.example.thesisapp.ui.component.HighlightJobItemWide
import com.example.thesisapp.ui.component.ThesisBottomBar
import com.example.thesisapp.ui.component.ThesisDivider
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.navigation.HomeSections
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun JobsPage(
    onNavigateToRoute: (String) -> Unit,
    onNavigateToStateDetails: (Long) -> Unit
) {
    ThesisScaffold(
        bottomBar = {
            ThesisBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.JOBS.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToRoute("addjob") },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = ""
                    )
                },
                backgroundColor = ThesisTheme.colors.chatLight,
                contentColor = ThesisTheme.colors.uiBackground,
                shape = Shapes.medium
            )
        }
    ) {
        val jobs = remember { advertisedJobs }
        val scroll = rememberScrollState(0)
        val gradientLight = ThesisTheme.colors.gradient6_1
        val gradientDark = ThesisTheme.colors.gradient6_2
        val gradientHeight = with(LocalDensity.current) {
            (6 * (HighlightCardBoxHeight + HighlightCardPadding).toPx())
        }

        Column {
            Text(
                text = stringResource(id = R.string.your_jobs_label),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4,
                color = ThesisTheme.colors.brand,
                modifier = Modifier.padding(16.dp)
            )

            ThesisDivider(thickness = 2.dp)

            LazyColumn {

                item {
                    Text(
                        text = stringResource(id = R.string.published_label),
                        style = MaterialTheme.typography.h6,
                        color = ThesisTheme.colors.brand,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 20.dp,
                            bottom = 16.dp
                        )
                    )
                }
                itemsIndexed(listOf(jobs.first())) { index, job ->

                    HighlightJobItemWide(
                        job = job,
                        index = index,
                        onJobClicked = onNavigateToStateDetails,
                        gradient = gradientLight,
                        gradientHeight = gradientHeight,
                        scroll = scroll.value,
                        isDetailsMode = false
                    )
                }
                item {
                    ThesisDivider(
                        thickness = 2.dp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                item {
                    Text(
                        text = stringResource(id = R.string.applied_for_label),
                        style = MaterialTheme.typography.h6,
                        color = ThesisTheme.colors.brand,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 20.dp,
                            bottom = 16.dp
                        )
                    )
                }
                itemsIndexed(jobs.filter { job -> ((job.id?.div(2))?.rem(2)) != 0L }) { index, job ->

                    HighlightJobItemWide(
                        job = job,
                        index = index,
                        onJobClicked = onNavigateToStateDetails,
                        gradient = gradientDark,
                        gradientHeight = gradientHeight,
                        scroll = scroll.value,
                        isDetailsMode = false
                    )
                }
                item {
                    Spacer(
                        Modifier.windowInsetsBottomHeight(
                            WindowInsets.statusBars.add(WindowInsets(bottom = 56.dp))
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MyJobsPagePreview() {
    ThesisappTheme {
        JobsPage(
            onNavigateToRoute = {},
            onNavigateToStateDetails = {}
        )
    }
}