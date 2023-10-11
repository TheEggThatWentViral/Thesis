package com.example.thesisapp.ui.job.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesisapp.domain.model.filters
import com.example.thesisapp.ui.component.FilterBar
import com.example.thesisapp.ui.component.HighlightCardPadding
import com.example.thesisapp.ui.component.HighlightJobItemWide
import com.example.thesisapp.ui.component.HighlightCardBoxHeight
import com.example.thesisapp.ui.component.ThesisDivider
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.component.advertisedJobs
import com.example.thesisapp.ui.theme.ThesisTheme
import com.example.thesisapp.ui.theme.ThesisappTheme

@Composable
fun JobListPage(
    nameId: Int,
    onJobClicked: (Long) -> Unit
) {

    ThesisScaffold {
        val jobs = remember { advertisedJobs }
        val filters = remember { filters }

        val scroll = rememberScrollState(0)
        val gradient = ThesisTheme.colors.gradient6_1
        val gradientHeight = with(LocalDensity.current) {
            (6 * (HighlightCardBoxHeight + HighlightCardPadding).toPx())
        }

        val name = when (nameId) {
            0 -> "Close to you"
            1 -> "Recently added"
            2 -> "High price"
            else -> "List"
        }

        Column {

            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4,
                color = ThesisTheme.colors.brand,
                modifier = Modifier.padding(16.dp)
            )

            ThesisDivider(thickness = 2.dp)

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            FilterBar(filters = filters, onShowFilters = {})

            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            LazyColumn {

                itemsIndexed(jobs) { index, job ->

                    HighlightJobItemWide(
                        job = job,
                        index = index,
                        onJobClicked = onJobClicked,
                        gradient = gradient,
                        gradientHeight = gradientHeight,
                        scroll = scroll.value
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun JobListPreview() {
    ThesisappTheme(
        darkTheme = false
    ) {
        JobListPage(
            onJobClicked = {},
            nameId = 1
        )
    }
}