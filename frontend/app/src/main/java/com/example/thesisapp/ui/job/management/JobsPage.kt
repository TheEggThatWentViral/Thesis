package com.example.thesisapp.ui.job.management

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.thesisapp.ui.component.ThesisBottomBar
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.navigation.HomeSections

@Composable
fun JobsPage(
    onNavigateToRoute: (String) -> Unit
) {
    ThesisScaffold(
        bottomBar = {
            ThesisBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.JOBS.route,
                navigateToRoute = onNavigateToRoute
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "My jobs",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}