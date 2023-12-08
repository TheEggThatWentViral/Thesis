package com.example.thesisapp.ui.map

import android.location.Location
import com.example.thesisapp.ui.map.clusters.MapClusterItem
import com.google.android.gms.maps.model.LatLng

data class MapViewState(
    val lastKnownLocation: Location?,
    val clusterItems: List<MapClusterItem>,
    val route: List<LatLng>
)
