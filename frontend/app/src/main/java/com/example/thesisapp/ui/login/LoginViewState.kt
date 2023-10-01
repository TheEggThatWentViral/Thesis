package com.example.thesisapp.ui.login

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.StateEventWithContent
import de.palm.composestateevents.consumed
data class LoginViewState(
    val loginSuccess: StateEvent = consumed,
    val loginError: StateEventWithContent<String> = consumed(),
    val rememberMe: Boolean = false,
    val username: String = "",
    val password: String = "",
    val usernameError: Boolean = false,
    val passwordError: Boolean = false
)
