package com.example.thesisapp.ui.job.detail

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed

data class JobDetailViewState(
    val applicationSuccess: StateEvent = consumed,
    val applicationError: StateEventWithContent<String> = consumed()
)
