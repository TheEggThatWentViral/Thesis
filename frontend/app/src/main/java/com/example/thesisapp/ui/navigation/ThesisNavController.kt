package com.example.thesisapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val AUTHENTICATION_ROUTE = "authentication"
    const val JOB_DETAIL_ROUTE = "detail"
    const val JOB_ID_KEY = "jobId"
    const val JOB_LIST_ROUTE = "list"
    const val JOB_LIST_NAME_KEY = "listName"
}

@Composable
fun rememberThesisNavController(
    navController: NavHostController = rememberNavController()
): ThesisNavController = remember(navController) {
    ThesisNavController(navController)
}

@Stable
class ThesisNavController(
    val navController: NavHostController,
) {
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true

                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToJobDetail(jobId: Long, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.JOB_DETAIL_ROUTE}/$jobId")
        }
    }

    fun navigateToJobList(nameId: Int, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(
                "${MainDestinations.JOB_LIST_ROUTE}/$nameId"
            )
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
