package com.example.thesisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
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
                        onNavigateToRoute = thesisNavController::navigateToBottomBarRoute
                    )
                }
            }
        }
    }

    private fun NavGraphBuilder.thesisNavGraph(
        upPress: () -> Unit,
        onNavigateToRoute: (String) -> Unit
    ) {
        navigation(
            route = MainDestinations.AUTHENTICATION_ROUTE,
            startDestination = AuthenticationSections.LOGIN.route
        ) {
            addAuthenticationGraph(onNavigateToRoute)
            addHomeGraph(onNavigateToRoute)
        }
    }
}
