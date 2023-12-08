package com.example.thesisapp.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesisapp.ui.map.clusters.MapClusterItem
import com.example.thesisapp.ui.map.clusters.MapClusterManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.google.maps.android.PolyUtil
import com.google.maps.model.LatLng
import com.google.maps.model.TravelMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val geoApiContext: GeoApiContext
) : ViewModel() {

    private val lastKnownLocation = Location("").apply {
        latitude = 47.4733880706
        longitude = 19.0598630905
    }

    private val _stateStream: MutableStateFlow<MapViewState> = MutableStateFlow(
        MapViewState(
            lastKnownLocation = lastKnownLocation,
            clusterItems = listOf(
                MapClusterItem(
                    id = 1L,
                    title = "Cleaning",
                    snippet = "Payment: 45 $",
                    position = com.google.android.gms.maps.model.LatLng(
                        47.47527183998187,
                        19.059524914435183
                    )
                ),
                MapClusterItem(
                    id = 1L,
                    title = "Driving",
                    snippet = "Payment: 35 $",
                    position = com.google.android.gms.maps.model.LatLng(
                        47.473172972497025,
                        19.05307649289998
                    )
                ),
                MapClusterItem(
                    id = 1L,
                    title = "Packaging",
                    snippet = "Payment: 40 $",
                    position = com.google.android.gms.maps.model.LatLng(
                        47.4705949449291,
                        19.061390913091483
                    )
                ),
                MapClusterItem(
                    id = 1L,
                    title = "Pet sitting",
                    snippet = "Payment: 15 $",
                    position = com.google.android.gms.maps.model.LatLng(
                        47.47354969432547,
                        19.055347947113205
                    )
                )
            ),
            route = listOf()
        )
    )
    val stateStream = _stateStream.asStateFlow()

    private var state: MapViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }

    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state = state.copy(
                        lastKnownLocation = task.result,
                    )
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    fun setupClusterManager(
        context: Context,
        map: GoogleMap,
        isSpecificJob: Boolean
    ): MapClusterManager {
        val clusterManager = MapClusterManager(context, map)

        if (isSpecificJob) {
            clusterManager.addItem(state.clusterItems.first())
        } else {
            clusterManager.addItems(state.clusterItems)
        }

        return clusterManager
    }

    fun getDirection(destination: LatLng) {
        viewModelScope.launch {
            val location = state.lastKnownLocation
            if (location != null) {
                val origin = LatLng(
                    location.latitude,
                    location.longitude
                )

                val result = DirectionsApi
                    .newRequest(geoApiContext)
                    .mode(TravelMode.WALKING)
                    .origin(origin)
                    .destination(destination)
                    .departureTime(DateTime())
                    .await()

                val decodedRoute = PolyUtil.decode(
                    result.routes[0].overviewPolyline.encodedPath
                )

                state = state.copy(route = decodedRoute)
            } else {
                return@launch
            }
        }
    }
}