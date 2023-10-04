package com.example.thesisapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thesisapp.R
import com.example.thesisapp.ui.home.HomePage
import com.example.thesisapp.ui.jobs.JobsPage
import com.example.thesisapp.ui.profile.ProfilePage

fun NavGraphBuilder.addHomeGraph(
    onNavigateToRoute: (String) -> Unit
) {
    composable(HomeSections.FEED.route) {
        HomePage(
            onNavigateToRoute = onNavigateToRoute,
            getAdvertisedJobs = { listOf() },
            onJobClick = {}
        )
    }
    composable(HomeSections.JOBS.route) {
        JobsPage(onNavigateToRoute = onNavigateToRoute)
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