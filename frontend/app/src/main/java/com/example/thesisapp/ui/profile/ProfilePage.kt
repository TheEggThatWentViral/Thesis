package com.example.thesisapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.HighlightCardPadding
import com.example.thesisapp.ui.component.HighlightCardWidth
import com.example.thesisapp.ui.component.HighlightedJob
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.ThesisBottomBar
import com.example.thesisapp.ui.component.ThesisDivider
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.component.diagonalGradientBorder
import com.example.thesisapp.ui.component.offsetGradientHorizontalBackground
import com.example.thesisapp.ui.navigation.HomeSections
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun ProfilePage(
    onNavigateToRoute: (String) -> Unit
) {
    ThesisScaffold(
        bottomBar = {
            ThesisBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.PROFILE.route,
                navigateToRoute = onNavigateToRoute
            )
        }
    ) {
        ThesisSurface {
            Box(
                modifier = Modifier
                    .background(
                        Brush.horizontalGradient(
                            colors = ThesisTheme.colors.gradient6_3,
                            startX = 300f,
                            endX = 2000f
                        )
                    )
                    .fillMaxSize()
            ) {
                Column {
                    LowerSurface()
                    UpperSurface()
                }

            }
        }
    }
}

@Composable
fun LowerSurface() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 32.dp, top = 32.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "Mike",
                style = MaterialTheme.typography.h4,
                color = ThesisTheme.colors.uiBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Ehrmantraut",
                style = MaterialTheme.typography.h4,
                color = ThesisTheme.colors.uiBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Image(
            painter = painterResource(id = R.drawable.job_placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun UpperSurface() {
    ThesisSurface(
        modifier = Modifier.fillMaxSize(),
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
    ) {
        LazyColumn {
            item { PersonalInfo() }
            item { Introduction() }
            item { JobsDone() }
        }
    }
}

@Composable
fun PersonalInfo() {
    Column(modifier = Modifier.padding(top = 20.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_personal_label),
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textPrimary
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                tint = ThesisTheme.colors.brand,
                contentDescription = stringResource(R.string.profile_personal_icon),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .diagonalGradientBorder(
                        colors = ThesisTheme.colors.interactiveSecondary,
                        shape = CircleShape,
                        borderSize = 3.dp
                    )
            )
        }

        ThesisDivider(
            color = ThesisTheme.colors.textPrimary.copy(alpha = 0.50f),
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "25",
                    style = MaterialTheme.typography.h6,
                    color = ThesisTheme.colors.textSecondary
                )
                Text(
                    text = stringResource(id = R.string.profile_age_label),
                    style = MaterialTheme.typography.h5,
                    color = ThesisTheme.colors.textHelp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bachelor degree",
                    style = MaterialTheme.typography.h6,
                    color = ThesisTheme.colors.textSecondary
                )
                Text(
                    text = stringResource(id = R.string.profile_studies_label),
                    style = MaterialTheme.typography.h5,
                    color = ThesisTheme.colors.textHelp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 4.dp, top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_ratings_label),
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textPrimary
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_rating),
                tint = ThesisTheme.colors.brand,
                contentDescription = stringResource(R.string.profile_ratings_icon),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .diagonalGradientBorder(
                        colors = ThesisTheme.colors.interactiveSecondary,
                        shape = CircleShape,
                        borderSize = 3.dp
                    )
            )
        }

        ThesisDivider(
            color = ThesisTheme.colors.textPrimary.copy(alpha = 0.50f),
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "4.2",
                    style = MaterialTheme.typography.h6,
                    color = ThesisTheme.colors.textSecondary
                )
                Text(
                    text = stringResource(id = R.string.profile_worker_label),
                    style = MaterialTheme.typography.h5,
                    color = ThesisTheme.colors.textHelp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "4.6",
                    style = MaterialTheme.typography.h6,
                    color = ThesisTheme.colors.textSecondary
                )
                Text(
                    text = stringResource(id = R.string.profile_publisher_label),
                    style = MaterialTheme.typography.h5,
                    color = ThesisTheme.colors.textHelp
                )
            }
        }
    }
}

@Composable
fun Introduction() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 4.dp, top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_introduction_label),
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textPrimary
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_introduction),
                tint = ThesisTheme.colors.brand,
                contentDescription = stringResource(R.string.profile_introduction_icon),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .diagonalGradientBorder(
                        colors = ThesisTheme.colors.interactiveSecondary,
                        shape = CircleShape,
                        borderSize = 3.dp
                    )
            )
        }

        ThesisDivider(
            color = ThesisTheme.colors.textPrimary.copy(alpha = 0.50f),
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = "Hi, my name is Mike Ehrmantraut and I'm a university student. Currently I'm looking for a summer job as I'm trying to save money for a holiday with my friends. I'm studying IT so I would prefer to do any IT related stuff.",
            style = MaterialTheme.typography.body2,
            color = ThesisTheme.colors.textHelp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        )
    }
}

@Composable
fun JobsDone() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.profile_jobsdone_label),
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textPrimary
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_done),
                tint = ThesisTheme.colors.brand,
                contentDescription = stringResource(R.string.profile_jobsdone_label),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .diagonalGradientBorder(
                        colors = ThesisTheme.colors.interactiveSecondary,
                        shape = CircleShape,
                        borderSize = 3.dp
                    )
            )
        }

        ThesisDivider(
            color = ThesisTheme.colors.textPrimary.copy(alpha = 0.50f),
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.profile_as_worker_label),
            style = MaterialTheme.typography.h6,
            color = ThesisTheme.colors.textSecondary,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 16.dp)
        )

        HighlightedJob(
            index = 1,
            jobs = advertisedJobs,
            onJobClick = {}
        )

        Text(
            text = stringResource(id = R.string.profile_as_publisher_label),
            style = MaterialTheme.typography.h6,
            color = ThesisTheme.colors.textSecondary,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp, start = 16.dp)
        )

        HighlightedJob(
            index = 2,
            jobs = advertisedJobs,
            onJobClick = {}
        )
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    ThesisappTheme {
        ProfilePage(
            onNavigateToRoute = {}
        )
    }
}