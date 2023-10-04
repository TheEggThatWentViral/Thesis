package com.example.thesisapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.domain.model.CollectionType
import com.example.thesisapp.domain.model.Filter
import com.example.thesisapp.domain.model.JobCollectionData
import com.example.thesisapp.domain.model.filters
import com.example.thesisapp.ui.component.FilterBar
import com.example.thesisapp.ui.component.JobCollection
import com.example.thesisapp.ui.component.ThesisBottomBar
import com.example.thesisapp.ui.component.ThesisDivider
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.ThesisSurface
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.navigation.HomeSections
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun HomePage(
    onNavigateToRoute: (String) -> Unit,
    getAdvertisedJobs: () -> List<JobCollectionData>,
    onJobClick: (Long) -> Unit
) {
    val jobCollections = remember {
        listOf(
            JobCollectionData(
                id = 1L,
                jobs = advertisedJobs,
                name = "Close to you",
                type = CollectionType.Highlight
            ),
            JobCollectionData(
                id = 2L,
                jobs = advertisedJobs,
                name = "Recently added"
            ),
            JobCollectionData(
                id = 3L,
                jobs = advertisedJobs,
                name = "High price",
                type = CollectionType.Highlight
            )
        )
    }
    val filters = remember { filters }

    ThesisScaffold(
        bottomBar = {
            ThesisBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        }
    ) {
        Feed(
            jobCollectionData = jobCollections,
            filters = filters,
            onJobClick = onJobClick
        )
    }
}

@Composable
fun Feed(
    jobCollectionData: List<JobCollectionData>,
    filters: List<Filter>,
    onJobClick: (Long) -> Unit
) {
    ThesisSurface(modifier = Modifier.fillMaxSize()) {
        Box {
            JobCollectionList(jobCollectionData, filters, onJobClick)
            //DestinationBar()
        }
    }
}

@Composable
private fun JobCollectionList(
    jobCollectionData: List<JobCollectionData>,
    filters: List<Filter>,
    onJobClick: (Long) -> Unit
) {
    var filtersVisible by rememberSaveable { mutableStateOf(false) }

    Box {
        LazyColumn {

            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
                FilterBar(filters, onShowFilters = { filtersVisible = true })
            }
            itemsIndexed(jobCollectionData) { index, jobCollectionData ->
                if (index > 0) {
                    ThesisDivider(thickness = 2.dp)
                }

                JobCollection(
                    jobCollectionData = jobCollectionData,
                    onJobClick = onJobClick,
                    index = index
                )
            }
        }
    }
}

@Preview
@Composable
fun FeedPreview() {
    ThesisappTheme {
        HomePage(
            onNavigateToRoute = {},
            getAdvertisedJobs = { listOf() },
            onJobClick = {}
        )
    }
}