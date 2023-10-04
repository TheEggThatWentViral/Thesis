package com.example.thesisapp.ui.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.thesisapp.R
import com.example.thesisapp.domain.model.Address
import com.example.thesisapp.domain.model.AdvertisedJob
import com.example.thesisapp.domain.model.CollectionType
import com.example.thesisapp.domain.model.JobCollectionData
import com.example.thesisapp.domain.model.JobState
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.util.mirroringIcon

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp

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
    highlight: Boolean = true
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
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
private fun HighlightedJob(
    index: Int,
    jobs: List<AdvertisedJob>,
    onJobClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState(0)
    val gradient = when ((index / 2) % 2) {
        0 -> ThesisTheme.colors.gradient6_1
        else -> ThesisTheme.colors.gradient6_2
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
            HighlightSnackItem(
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
private fun Jobs(
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
            SnackImage(
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
private fun HighlightSnackItem(
    snack: AdvertisedJob,
    onSnackClick: (Long) -> Unit,
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
                .clickable(onClick = { onSnackClick(snack.id ?: 1L) })
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
                        .offsetGradientBackground(gradient, gradientWidth, gradientOffset)
                )
                SnackImage(
                    imageUrl = snack.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = snack.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = ThesisTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = snack.description,
                style = MaterialTheme.typography.body1,
                color = ThesisTheme.colors.textHelp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun SnackImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    ThesisSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
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
        description = "A cleaning is need in my house",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = "30$",
        jobState = JobState.ACTIVE
    ),
    AdvertisedJob(
        id = 2L,
        title = "Packaging",
        description = "Pack orders in a warehouse",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = "45$",
        jobState = JobState.ACTIVE
    ),
    AdvertisedJob(
        id = 3L,
        title = "Driving",
        description = "Searching fro a driver",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = "50$",
        jobState = JobState.ACTIVE
    ),
    AdvertisedJob(
        id = 4L,
        title = "Pet sitting",
        description = "Watching out for dogs",
        address = Address(country = "", city = "", zipCode = "", street = "", number = ""),
        imageUrl = "",
        price = "26$",
        jobState = JobState.ACTIVE
    )
)