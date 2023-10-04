package com.example.thesisapp.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class JobCollectionData(
    val id: Long,
    val name: String,
    val jobs: List<AdvertisedJob>,
    val type: CollectionType = CollectionType.Normal
)

enum class CollectionType { Normal, Highlight }