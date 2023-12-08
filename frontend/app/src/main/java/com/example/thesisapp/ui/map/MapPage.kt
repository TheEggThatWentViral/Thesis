package com.example.thesisapp.ui.map

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thesisapp.R
import com.example.thesisapp.ui.component.ThesisScaffold
import com.example.thesisapp.ui.map.clusters.MapClusterItem
import com.example.thesisapp.ui.map.clusters.MapClusterManager
import com.example.thesisapp.ui.theme.Shapes
import com.example.thesisapp.ui.theme.ThesisTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@SuppressLint("PotentialBehaviorOverride")
@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapPage(
    state: MapViewState,
    setupClusterManager: (Context, GoogleMap, Boolean) -> MapClusterManager,
    jobId: Long,
    onPlanRouteClicked: (com.google.maps.model.LatLng) -> Unit
) {
    val mapProperties = MapProperties(
        isMyLocationEnabled = true
    )

    val cameraPositionState = rememberCameraPositionState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val isSpecificJob = jobId != 0L

    ThesisScaffold(
        topBar = {
            MapTopBar()
        },
        floatingActionButton = {
            if (isSpecificJob) {
                ExtendedFloatingActionButton(
                    onClick = {
                        onPlanRouteClicked(
                            com.google.maps.model.LatLng(
                                state.clusterItems[0].position.latitude,
                                state.clusterItems[0].position.longitude
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_route),
                            contentDescription = ""
                        )
                    },
                    text = { Text(text = "Route") },
                    backgroundColor = ThesisTheme.colors.checkBasic,
                    contentColor = ThesisTheme.colors.checkFocus,
                    shape = Shapes.medium
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = mapProperties,
                cameraPositionState = cameraPositionState
            ) {
                MapEffect(state.clusterItems) { map ->
                    if (state.clusterItems.isNotEmpty()) {
                        val clusterManager = setupClusterManager(context, map, isSpecificJob)
                        map.setOnCameraIdleListener(clusterManager)
                        map.setOnMarkerClickListener(clusterManager)
                        map.uiSettings.isZoomControlsEnabled = false
                        map.addPolyline(
                            PolylineOptions().addAll(state.route)
                        )

                        val camStartingPosition = getCamStartingPosition(
                            items = state.clusterItems,
                            isSpecificJob = isSpecificJob,
                            jobId = jobId
                        )

                        val zoom = getZoomValue(isSpecificJob)

                        map.setOnMapLoadedCallback {
                            if (state.clusterItems.isNotEmpty()) {
                                scope.launch {
                                    cameraPositionState.animate(
                                        update = CameraUpdateFactory.newLatLngZoom(
                                            camStartingPosition,
                                            zoom
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MapTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(ThesisTheme.colors.brand)
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "",
            tint = ThesisTheme.colors.uiBackground,
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.padding(horizontal = 75.dp))

        Text(
            text = stringResource(id = R.string.map_label),
            style = MaterialTheme.typography.h6,
            color = ThesisTheme.colors.uiBackground
        )
    }
}

private fun getCamStartingPosition(
    items: List<MapClusterItem>,
    isSpecificJob: Boolean,
    jobId: Long
): LatLng {
    return if (isSpecificJob) {
        items.first { item ->
            item.id == jobId
        }.position
    } else {
        items.first().position
    }
}

private fun getZoomValue(isSpecificJob: Boolean): Float {
    return if (isSpecificJob) {
        17F
    } else {
        15F
    }
}