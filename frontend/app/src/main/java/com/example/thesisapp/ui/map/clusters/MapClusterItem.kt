package com.example.thesisapp.ui.map.clusters

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MapClusterItem(
    val id: Long?,
    private val title: String,
    private val snippet: String,
    private val position: LatLng
) : ClusterItem {
    override fun getPosition() = position

    override fun getTitle() = title

    override fun getSnippet() = snippet
}
