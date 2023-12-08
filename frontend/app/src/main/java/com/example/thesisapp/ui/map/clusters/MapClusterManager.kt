package com.example.thesisapp.ui.map.clusters

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.MarkerManager

class MapClusterManager(
    context: Context,
    googleMap: GoogleMap,
): ClusterManager<ClusterItem>(context, googleMap, MarkerManager(googleMap))