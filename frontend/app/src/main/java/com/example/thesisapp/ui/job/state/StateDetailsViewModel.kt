package com.example.thesisapp.ui.job.state

import androidx.lifecycle.ViewModel
import com.example.thesisapp.config.ConfigurationProvider
import com.example.thesisapp.ui.chat.ChatViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StateDetailsViewModel @Inject constructor(
    private val configurationProvider: ConfigurationProvider
): ViewModel() {

    private val _stateStream: MutableStateFlow<StateDetailsViewState> = MutableStateFlow(
        StateDetailsViewState(configurationProvider.username ?: "")
    )
    val stateStream = _stateStream.asStateFlow()

    private var state: StateDetailsViewState
        get() = _stateStream.value
        set(newState) {
            _stateStream.update { newState }
        }
}