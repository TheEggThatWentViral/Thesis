package com.example.thesisapp.domain.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
class Filter(
    val name: String,
    enabled: Boolean = false,
    val icon: ImageVector? = null
) {
    val enabled = mutableStateOf(enabled)
}

val filters = listOf(
    Filter(name = "Indoor"),
    Filter(name = "Outdoor"),
    Filter(name = "Physical"),
    Filter(name = "Skill"),
    Filter(name = "Flexible"),
    Filter(name = "Full-day"),
    Filter(name = "Team"),
    Filter(name = "Single")
)