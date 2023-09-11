package com.example.thesisapp.domain.model

import com.squareup.moshi.Json

enum class JobState {
    @Json(name = "Active")
    ACTIVE,

    @Json(name = "Inactive")
    INACTIVE,

    @Json(name = "Done")
    DONE
}
