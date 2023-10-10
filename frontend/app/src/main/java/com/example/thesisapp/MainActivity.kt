package com.example.thesisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.thesisapp.ui.job.detail.JobDetailPage
import com.example.thesisapp.ui.job.list.JobListPage
import com.example.thesisapp.ui.navigation.AuthenticationSections
import com.example.thesisapp.ui.navigation.MainDestinations
import com.example.thesisapp.ui.navigation.addAuthenticationGraph
import com.example.thesisapp.ui.navigation.addHomeGraph
import com.example.thesisapp.ui.navigation.rememberThesisNavController
import com.example.thesisapp.ui.theme.ThesisappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ThesisappTheme {

                val thesisNavController = rememberThesisNavController()

                NavHost(
                    navController = thesisNavController.navController,
                    startDestination = MainDestinations.AUTHENTICATION_ROUTE
                ) {
                    thesisNavGraph(
                        upPress = thesisNavController::upPress,
                        onNavigateToRoute = thesisNavController::navigateToBottomBarRoute,
                        onJobSelected = thesisNavController::navigateToJobDetail,
                        onNavigateToList = thesisNavController::navigateToJobList
                    )
                }
            }
        }
    }

    private fun NavGraphBuilder.thesisNavGraph(
        upPress: () -> Unit,
        onNavigateToRoute: (String) -> Unit,
        onJobSelected: (Long, NavBackStackEntry) -> Unit,
        onNavigateToList: (Int, NavBackStackEntry) -> Unit
    ) {
        navigation(
            route = MainDestinations.AUTHENTICATION_ROUTE,
            startDestination = AuthenticationSections.LOGIN.route
        ) {
            addAuthenticationGraph(onNavigateToRoute)
            addHomeGraph(onNavigateToRoute, onJobSelected, onNavigateToList)
        }

        composable(
            route = "${MainDestinations.JOB_DETAIL_ROUTE}/{${MainDestinations.JOB_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.JOB_ID_KEY) { type = NavType.LongType})
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val jobId = arguments.getLong(MainDestinations.JOB_ID_KEY)
            JobDetailPage(jobId, upPress)
        }

        composable(
            route = "${MainDestinations.JOB_LIST_ROUTE}/{${MainDestinations.JOB_LIST_NAME_KEY}}",
            arguments = listOf(navArgument(MainDestinations.JOB_LIST_NAME_KEY) { type = NavType.IntType})
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val name = arguments.getInt(MainDestinations.JOB_LIST_NAME_KEY)
            JobListPage(name) { id -> onJobSelected(id, backStackEntry) }
        }
    }
}
