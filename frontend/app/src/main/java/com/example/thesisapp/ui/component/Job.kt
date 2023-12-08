package com.example.thesisapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.Address
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.CollectionType
import com.example.thesisapp.domain.model.Coordinates
import com.example.thesisapp.domain.model.JobCollectionData
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.domain.model.User
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme
import com.example.thesisapp.ui.util.mirroringIcon

val HighlightCardWidth = 170.dp
val HighlightCardBoxHeight = 100.dp
val HighlightCardPadding = 16.dp


// The Cards show a gradient which spans 3 cards and scrolls with parallax.
private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

@Composable
fun JobCollection(
    jobCollectionData: JobCollectionData,
    onJobClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    index: Int = 0,
    highlight: Boolean = true,
    onCollectionClicked: (Int) -> Unit,
    name: Int
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
                .clickable { onCollectionClicked(name) }
        ) {
            Text(
                text = jobCollectionData.name,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = mirroringIcon(
                        ltrIcon = Icons.Outlined.ArrowForward,
                        rtlIcon = Icons.Outlined.ArrowBack
                    ),
                    tint = ThesisTheme.colors.brand,
                    contentDescription = null
                )
            }
        }
        if (highlight && jobCollectionData.type == CollectionType.Highlight) {
            HighlightedJob(index, jobCollectionData.jobs, onJobClick)
        } else {
            Jobs(jobCollectionData.jobs, onJobClick)
        }
    }
}

@Composable
fun HighlightedJob(
    index: Int,
    jobs: List<AdvertisedJob>,
    onJobClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)
    val gradient = when ((index / 2) % 2) {
        0 -> ThesisTheme.colors.gradient6_2
        else -> ThesisTheme.colors.gradient6_1
    }
    // The Cards show a gradient which spans 3 cards and scrolls with parallax.
    val gradientWidth = with(LocalDensity.current) {
        (6 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp)
    ) {
        itemsIndexed(jobs) { index, snack ->
            HighlightJobItem(
                snack,
                onJobClick,
                index,
                gradient,
                gradientWidth,
                scroll.value
            )
        }
    }
}

@Composable
fun Jobs(
    jobs: List<AdvertisedJob>,
    onJobClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(jobs) { snack ->
            JobItem(snack, onJobClick)
        }
    }
}

@Composable
fun JobItem(
    job: AdvertisedJob,
    onJobClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    ThesisSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp,
            bottom = 8.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = { onJobClick(job.id ?: 1L) })
                .padding(8.dp)
        ) {
            JobImage(
                imageUrl = job.imageUrl,
                elevation = 4.dp,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = job.title,
                style = MaterialTheme.typography.subtitle1,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun HighlightJobItem(
    job: AdvertisedJob,
    onJobClicked: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientWidth: Float,
    scroll: Int,
    modifier: Modifier = Modifier
) {
    val left = index * with(LocalDensity.current) {
        (HighlightCardWidth + HighlightCardPadding).toPx()
    }

    ThesisCard(
        modifier = modifier
            .size(
                width = 170.dp,
                height = 250.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onJobClicked(job.id ?: 1L) }
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = left - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientHorizontalBackground(
                            gradient,
                            gradientWidth,
                            gradientOffset
                        )
                )
                JobImage(
                    imageUrl = job.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = job.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = job.price.toString() + " $",
                style = MaterialTheme.typography.h5,
                color = ThesisTheme.colors.textHelp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.End)
            )
        }
    }
}

@Composable
fun HighlightJobItemWide(
    job: AdvertisedJob,
    onJobClicked: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientHeight: Float,
    scroll: Int,
    modifier: Modifier = Modifier,
    isDetailsMode: Boolean = true
) {
    val top = index * with(LocalDensity.current) {
        (HighlightCardBoxHeight + HighlightCardPadding).toPx()
    }

    ThesisCard(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(
                bottom = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onJobClicked(job.id ?: 1L) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = top - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientVerticalBackground(gradient, gradientHeight, gradientOffset)
                )
                JobImage(
                    imageUrl = job.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .width(240.dp)
                        .align(Alignment.BottomCenter),
                    shape = MaterialTheme.shapes.medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = job.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = job.price.toString() + " $",
                    style = MaterialTheme.typography.body2,
                    color = ThesisTheme.colors.textHelp,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = "   |   10 km",
                    style = MaterialTheme.typography.body2,
                    color = ThesisTheme.colors.textHelp
                )

                Text(
                    text = "   |   " + job.time,
                    style = MaterialTheme.typography.body2,
                    color = ThesisTheme.colors.textHelp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            if (isDetailsMode) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.list_details_label),
                        style = MaterialTheme.typography.caption,
                        color = ThesisTheme.colors.brand
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        tint = ThesisTheme.colors.brand,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(22.dp)
                            .diagonalGradientBorder(
                                colors = ThesisTheme.colors.interactiveSecondary,
                                shape = CircleShape
                            )
                    )
                }
            } else {

                val iconState = when (job.jobState) {
                    JobState.ACTIVE -> R.drawable.ic_question
                    JobState.INACTIVE -> R.drawable.ic_warning
                    JobState.APPROVED -> R.drawable.ic_done
                    else -> R.drawable.ic_done
                }

                val colorState = when (job.jobState) {
                    JobState.ACTIVE -> ThesisTheme.colors.orange
                    JobState.INACTIVE -> ThesisTheme.colors.checkFocus
                    JobState.APPROVED -> ThesisTheme.colors.green
                    else -> ThesisTheme.colors.green
                }

                Row(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .align(Alignment.End)
                ) {

                    if (job.newMessage) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_new),
                            tint = ThesisTheme.colors.checkFocus,
                            contentDescription = stringResource(R.string.list_arrow_icon),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Icon(
                        painter = painterResource(id = iconState),
                        tint = colorState,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(22.dp)
                            .border(
                                width = 2.dp,
                                color = colorState,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun HighlightPersonItemWide(
    user: User,
    onUserClicked: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientHeight: Float,
    scroll: Int,
    modifier: Modifier = Modifier,
    isDetailsMode: Boolean = true,
    onChatClicked: (String) -> Unit,
    username: String
) {
    val top = index * with(LocalDensity.current) {
        (HighlightCardBoxHeight + HighlightCardPadding).toPx()
    }

    ThesisCard(
        modifier = modifier
            .height(275.dp)
            .fillMaxWidth()
            .padding(
                bottom = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = {})
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = top - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientVerticalBackground(gradient, gradientHeight, gradientOffset)
                )
                JobImage(
                    imageUrl = user.profilePicture,
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .align(Alignment.BottomCenter),
                    shape = CircleShape
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            val name = if (username == "mike") {
                "Kieran Brown"
            } else {
                "Mike Winger"
            }

            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (isDetailsMode) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Rate your publisher",
                        style = MaterialTheme.typography.body2,
                        color = ThesisTheme.colors.textHelp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_full_star),
                        tint = ThesisTheme.colors.checkFocus,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_full_star),
                        tint = ThesisTheme.colors.checkFocus,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_full_star),
                        tint = ThesisTheme.colors.checkFocus,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outlined),
                        tint = ThesisTheme.colors.checkBasic,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outlined),
                        tint = ThesisTheme.colors.checkBasic,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    )
                }
            } else {

               /* val iconState = when (job.jobState) {
                    JobState.ACTIVE -> R.drawable.ic_question
                    JobState.INACTIVE -> R.drawable.ic_warning
                    JobState.APPROVED -> R.drawable.ic_done
                    else -> R.drawable.ic_done
                }

                val colorState = when (job.jobState) {
                    JobState.ACTIVE -> ThesisTheme.colors.orange
                    JobState.INACTIVE -> ThesisTheme.colors.checkFocus
                    JobState.APPROVED -> ThesisTheme.colors.green
                    else -> ThesisTheme.colors.green
                }

                Row(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .align(Alignment.End)
                ) {

                    if (job.newMessage) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat_new),
                            tint = ThesisTheme.colors.checkFocus,
                            contentDescription = stringResource(R.string.list_arrow_icon),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Icon(
                        painter = painterResource(id = iconState),
                        tint = colorState,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(22.dp)
                            .border(
                                width = 2.dp,
                                color = colorState,
                                shape = CircleShape
                            )
                    )
                }*/
            }
        }
    }
}

@Composable
fun HighlightPersonItemRatingWide(
    user: User,
    onUserClicked: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientHeight: Float,
    scroll: Int,
    modifier: Modifier = Modifier,
    isDetailsMode: Boolean = true,
    onChatClicked: (String) -> Unit,
    username: String
) {
    val top = index * with(LocalDensity.current) {
        (HighlightCardBoxHeight + HighlightCardPadding).toPx()
    }

    ThesisCard(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(
                bottom = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = {})
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = top - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientVerticalBackground(gradient, gradientHeight, gradientOffset)
                )
                JobImage(
                    imageUrl = user.profilePicture,
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .align(Alignment.BottomCenter),
                    shape = CircleShape
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            val name = if (username == "mike") {
                "Kieran Brown"
            } else {
                "Mike Winger"
            }

            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            if (isDetailsMode) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                        .clickable { onChatClicked("chat") }
                ) {
                    Text(
                        text = "Chat",
                        style = MaterialTheme.typography.caption,
                        color = ThesisTheme.colors.brand
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        tint = ThesisTheme.colors.brand,
                        contentDescription = stringResource(R.string.list_arrow_icon),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(22.dp)
                            .diagonalGradientBorder(
                                colors = ThesisTheme.colors.interactiveSecondary,
                                shape = CircleShape
                            )
                    )
                }
            } else {

            }
        }
    }
}


@Composable
fun JobImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    shape: Shape = CircleShape
) {
    ThesisSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
       /* AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(R.drawable.job_placeholder),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )*/
        Image(
            painter = painterResource(id = R.drawable.job_placeholder),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

val advertisedJobs = listOf(
    AdvertisedJob(
        id = 1L,
        title = "Cleaning",
        description = "A cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house v cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house",
        address = Address(country = "Hungary", city = "Budapest", zipCode = "1067", street = "Rákóczi utca", number = "10."),
        imageUrl = "",
        price = 30,
        jobState = JobState.ACTIVE,
        coordinates = Coordinates(0L, 0L),
        time = "8 hours",
        newMessage = true
    ),
    AdvertisedJob(
        id = 2L,
        title = "Packaging",
        description = "Pack orders in a warehouse",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 45,
        jobState = JobState.APPROVED,
        coordinates = Coordinates(0L, 0L),
        time = "12 hours"
    ),
    AdvertisedJob(
        id = 3L,
        title = "Driving",
        description = "Searching fro a driver",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 50,
        jobState = JobState.INACTIVE,
        coordinates = Coordinates(0L, 0L),
        time = "2 days",
        newMessage = true
    ),
    AdvertisedJob(
        id = 4L,
        title = "Pet sitting",
        description = "Watching out for dogs",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 26,
        jobState = JobState.APPROVED,
        coordinates = Coordinates(0L, 0L),
        time = "4 hours"
    )
)

val peopleJobs = listOf(
    AdvertisedJob(
        id = 1L,
        title = "Nate",
        description = "A cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house v cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house cleaning is need in my house",
        address = Address(country = "Hungary", city = "Budapest", zipCode = "1067", street = "Rákóczi utca", number = "10."),
        imageUrl = "",
        price = 30,
        jobState = JobState.ACTIVE,
        coordinates = Coordinates(0L, 0L),
        time = "8 hours",
        newMessage = true
    ),
    AdvertisedJob(
        id = 2L,
        title = "Kiara",
        description = "Pack orders in a warehouse",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 45,
        jobState = JobState.APPROVED,
        coordinates = Coordinates(0L, 0L),
        time = "12 hours"
    ),
    AdvertisedJob(
        id = 3L,
        title = "Ann",
        description = "Searching fro a driver",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 50,
        jobState = JobState.INACTIVE,
        coordinates = Coordinates(0L, 0L),
        time = "2 days",
        newMessage = true
    ),
    AdvertisedJob(
        id = 4L,
        title = "Thomas",
        description = "Watching out for dogs",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = 26,
        jobState = JobState.APPROVED,
        coordinates = Coordinates(0L, 0L),
        time = "4 hours"
    )
)

val users = listOf(
    User(
        id = 1L,
        firstName = "Hannah",
        lastName = "Rose",
        username = "hannahrose",
        email = "hannahr@gmail.com",
        password = "1234",
        roles = listOf(),
        jobsDone = listOf(),
        workerRating = 4.2f,
        publisherRating = 4.0f,
        profilePicture = ""
    ),
    User(
        id = 2L,
        firstName = "Travis",
        lastName = "Kelce",
        username = "traviskelce",
        email = "travisk@gmail.com",
        password = "1234",
        roles = listOf(),
        jobsDone = listOf(),
        workerRating = 3.5f,
        publisherRating = 3.9f,
        profilePicture = ""
    ),
    User(
        id = 3L,
        firstName = "Micheal",
        lastName = "Styles",
        username = "micheal",
        email = "styles@gmail.com",
        password = "1234",
        roles = listOf(),
        jobsDone = listOf(),
        workerRating = 4.5f,
        publisherRating = 4.1f,
        profilePicture = ""
    )
)

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun JobCardPreview() {
    ThesisappTheme {
        val job = advertisedJobs.first()
        HighlightJobItem(
            job = job,
            onJobClicked = { },
            index = 0,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientWidth = gradientWidth,
            scroll = 0
        )
    }
}

@Preview
@Composable
fun WideJobCardPreview() {
    ThesisappTheme {
        val job = advertisedJobs.first()
        HighlightJobItemWide(
            job = job,
            onJobClicked = { },
            index = 0,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientHeight = gradientWidth,
            scroll = 0
        )
    }
}

@Preview
@Composable
fun WideMyJobCardPreview() {
    ThesisappTheme {
        val job = advertisedJobs.first()
        HighlightJobItemWide(
            job = job,
            onJobClicked = { },
            index = 0,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientHeight = gradientWidth,
            scroll = 0,
            isDetailsMode = false
        )
    }
}

@Preview
@Composable
fun WidePersonCardPreview() {
    ThesisappTheme {
        val user = users.first()
        HighlightPersonItemWide(
            user = user,
            onUserClicked = { },
            index = 0,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientHeight = gradientWidth,
            scroll = 0,
            onChatClicked = {},
            username = ""
        )
    }
}

@Preview
@Composable
fun WidePersonCardRatingPreview() {
    ThesisappTheme {
        val user = users.first()
        HighlightPersonItemRatingWide(
            user = user,
            onUserClicked = { },
            index = 0,
            gradient = ThesisTheme.colors.gradient6_1,
            gradientHeight = gradientWidth,
            scroll = 0,
            onChatClicked = {},
            username = ""
        )
    }
}