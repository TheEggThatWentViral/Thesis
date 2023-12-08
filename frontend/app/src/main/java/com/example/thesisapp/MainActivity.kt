package com.example.thesisapp

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.thesisapp.ui.chat.ChatPage
import com.example.thesisapp.ui.chat.ChatViewModel
import com.example.thesisapp.ui.job.addnew.AddNewJobPage
import com.example.thesisapp.ui.job.detail.JobDetailPage
import com.example.thesisapp.ui.job.detail.JobDetailViewModel
import com.example.thesisapp.ui.job.list.JobListPage
import com.example.thesisapp.ui.job.state.StateDetailsPage
import com.example.thesisapp.ui.job.state.StateDetailsViewModel
import com.example.thesisapp.ui.map.MapPage
import com.example.thesisapp.ui.map.MapViewModel
import com.example.thesisapp.ui.navigation.AuthenticationSections
import com.example.thesisapp.ui.navigation.MainDestinations
import com.example.thesisapp.ui.navigation.addAuthenticationGraph
import com.example.thesisapp.ui.navigation.addHomeGraph
import com.example.thesisapp.ui.navigation.rememberThesisNavController
import com.example.thesisapp.ui.theme.ThesisappTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WindowCompat.setDecorFitsSystemWindows(window, false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        askPermissions()

        FirebaseMessaging.getInstance().subscribeToTopic("/topics/new-application")

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
                        onNavigateToList = thesisNavController::navigateToJobList,
                        onNavigateToStateDetails = thesisNavController::navigateToStateDetails,
                        onNavigateToMap = thesisNavController::navigateToMap
                    )
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                mapViewModel.getDeviceLocation(fusedLocationProviderClient)
            }
        }

    private fun askPermissions() = when {
        ContextCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            mapViewModel.getDeviceLocation(fusedLocationProviderClient)
        }
        else -> {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    private fun NavGraphBuilder.thesisNavGraph(
        upPress: () -> Unit,
        onNavigateToRoute: (String) -> Unit,
        onJobSelected: (Long, NavBackStackEntry) -> Unit,
        onNavigateToList: (Int, NavBackStackEntry) -> Unit,
        onNavigateToStateDetails: (Long, NavBackStackEntry) -> Unit,
        onNavigateToMap: (Long, NavBackStackEntry) -> Unit
    ) {
        navigation(
            route = MainDestinations.AUTHENTICATION_ROUTE,
            startDestination = AuthenticationSections.LOGIN.route
        ) {
            addAuthenticationGraph(onNavigateToRoute)
            addHomeGraph(
                onNavigateToRoute,
                onJobSelected,
                onNavigateToList,
                onNavigateToStateDetails,
                onNavigateToMap
            )
        }

        composable(
            route = "${MainDestinations.JOB_DETAIL_ROUTE}/" +
                    "{${MainDestinations.JOB_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.JOB_ID_KEY) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val jobId = arguments.getLong(MainDestinations.JOB_ID_KEY)

            val jobDetailViewModel: JobDetailViewModel = hiltViewModel()
            val state = jobDetailViewModel.stateStream.collectAsState().value

            JobDetailPage(
                jobId = jobId,
                upPress = upPress,
                state = state,
                onApplyClicked = jobDetailViewModel::applyForJob
            )
        }

        composable(
            route = "${MainDestinations.JOB_LIST_ROUTE}/{${MainDestinations.JOB_LIST_NAME_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.JOB_LIST_NAME_KEY) { type = NavType.IntType}
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val name = arguments.getInt(MainDestinations.JOB_LIST_NAME_KEY)

            JobListPage(name) { id -> onJobSelected(id, backStackEntry) }
        }

        composable(
            route = "${MainDestinations.JOB_STATE_DETAIL_ROUTE}/{${MainDestinations.JOB_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.JOB_ID_KEY) { type = NavType.LongType})
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val jobId = arguments.getLong(MainDestinations.JOB_ID_KEY)

            val viewModel: StateDetailsViewModel = hiltViewModel()
            val state = viewModel.stateStream.collectAsState().value

            StateDetailsPage(
                jobId = jobId,
                onNavigateToRoute = onNavigateToRoute,
                state = state,
                openMap = { id -> onNavigateToMap(id, backStackEntry) }
            )
        }

        composable(route = "chat") {
            val viewModel: ChatViewModel = hiltViewModel()
            val state = viewModel.stateStream.collectAsState().value

            ChatPage(
                upPress = upPress,
                onSendMessageClicked = viewModel::sendMessage,
                state = state,
                navigateToRoute = onNavigateToRoute
            )
        }

        composable(
            route = "${MainDestinations.MAP_ROUTE}/{${MainDestinations.JOB_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.JOB_ID_KEY) { type = NavType.LongType})
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val jobId = arguments.getLong(MainDestinations.JOB_ID_KEY)

            val state = mapViewModel.stateStream.collectAsState().value
            
            MapPage(
                state = state,
                setupClusterManager = mapViewModel::setupClusterManager,
                jobId = jobId,
                onPlanRouteClicked = mapViewModel::getDirection
            )
        }

        composable(route = "addjob") {
            AddNewJobPage()
        }
    }
}
