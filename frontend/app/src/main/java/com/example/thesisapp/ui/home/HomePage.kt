package com.example.thesisapp.ui.home

import androidx.compose.runtime.Composable
import com.example.thesisapp.ui.component.ThesisScaffold

@Composable
fun HomePage(
    onNavigateToRoute: (String) -> Unit
) {
    ThesisScaffold(
        bottomBar = {
            ThesisBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        }
    ) {
        Feed()
    }
}

@Composable
fun Feed(

) {}