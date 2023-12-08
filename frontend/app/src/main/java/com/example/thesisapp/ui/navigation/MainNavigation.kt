package com.example.thesisapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thesisapp.R
import com.example.thesisapp.ui.job.feed.HomePage
import com.example.thesisapp.ui.job.management.JobsPage
import com.example.thesisapp.ui.profile.ProfilePage

fun NavGraphBuilder.addHomeGraph(
    onNavigateToRoute: (String) -> Unit,
    onJobSelected: (Long, NavBackStackEntry) -> Unit,
    onNavigateToList: (Int, NavBackStackEntry) -> Unit,
    onNavigateToStateDetails: (Long, NavBackStackEntry) -> Unit,
    onNavigateToMap: (Long, NavBackStackEntry) -> Unit
) {
    composable(HomeSections.FEED.route) { from ->
        HomePage(
            onNavigateToRoute = onNavigateToRoute,
            getAdvertisedJobs = { listOf() },
            onJobClick = { id -> onJobSelected(id, from) },
            onNavigateToList = { name -> onNavigateToList(name, from) },
            onNavigateToMap = { id -> onNavigateToMap(id, from) }
        )
    }
    composable(HomeSections.JOBS.route) { from ->
        JobsPage(
            onNavigateToRoute = onNavigateToRoute,
            onNavigateToStateDetails = { id -> onNavigateToStateDetails(id, from)}
        )
    }
    composable(HomeSections.PROFILE.route) {
        ProfilePage(onNavigateToRoute = onNavigateToRoute)
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    JOBS(R.string.home_jobs, Icons.Outlined.Edit, "home/jobs"),
    FEED(R.string.home_feed, Icons.Outlined.Home, "home/feed"),
    PROFILE(R.string.home_profile, Icons.Outlined.AccountCircle, "home/profile")
}